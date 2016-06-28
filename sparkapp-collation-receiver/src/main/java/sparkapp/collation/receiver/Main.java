package sparkapp.collation.receiver;


import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import kafka.serializer.StringDecoder;
import sparkapp.collation.receiver.config.DaoConfig;
import sparkapp.collation.receiver.config.MapperConfig;
import sparkapp.collation.receiver.config.ServiceConfig;
import usertracker.browser.mapper.impl.VisitorLogStringMapper;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.service.VisitorLogService;
public class Main {

	public static void main(String [] args) throws Exception {
		
		Logger.getLogger("sparkapp.collation.receiver.Main").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.dstream.DStream").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.dstream.WindowedDStream").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.DStreamGraph").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.scheduler.JobGenerator").setLevel(Level.INFO);

		ApplicationContext ctx = new AnnotationConfigApplicationContext(DaoConfig.class, ServiceConfig.class, MapperConfig.class);

		VisitorLogService visitorLogService = (VisitorLogService) ctx.getBean("visitorLogService");
		VisitorLogStringMapper visitorLogStringMapper = (VisitorLogStringMapper) ctx.getBean("visitorLogStringMapper");
		
		String brokers = args[0];
		String topics = args[1];

		// Create context with a 2 seconds batch interval
		SparkConf sparkConf = new SparkConf().setAppName("CollationReceiver");

		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		JavaStreamingContext jssc = new JavaStreamingContext(sc, new Duration(5000));
		SQLContext sqlContext = new SQLContext(sc);

		Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", brokers);

		// Create direct kafka stream with brokers and topics
		JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(jssc, String.class, String.class,
				StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet);
		
		if(createNewTable(args)){
			visitorLogService.creatTable();
		}
		

		// Get the lines, split them into words, count the words and print
		JavaDStream<String> lines = messages.map(line -> {
			return line._2();
		});
		//
		lines.foreachRDD(items -> {

			JavaRDD<VisitorLogModel> rowRDD = (JavaRDD) items.map(item -> {
				return visitorLogStringMapper.map(item);
			});

			List<VisitorLogModel> events = rowRDD.collect();
			for (VisitorLogModel event : events) {
				System.out.println("Saving..");
				event.setId(String.valueOf(new Date().getTime()));
				visitorLogService.save(event);
			}

			
			if (events.size() > 0 && broadcast(args)) {
				try{
					RestTemplate rt = new RestTemplate();

					rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

					rt.getMessageConverters().add(new StringHttpMessageConverter());

					String uri = new String("http://103.253.145.213:8191/webapp-poc/notifyEvents");

					String result = rt.postForObject(uri, events, String.class);
				}catch(Exception e){
					
				}
				
			}
		});
		jssc.start();
		jssc.awaitTermination();
	}
	public static boolean createNewTable(String[] args){
		if(args.length > 2){
			return "true".equals(args[2]);
		} else {
			return false;
		}
	}
	public static boolean broadcast(String[] args){
		if(args.length > 3){
			return "true".equals(args[3]);
		} else {
			return false;
		}
	}
}
