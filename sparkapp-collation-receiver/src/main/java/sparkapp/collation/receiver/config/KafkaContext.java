package sparkapp.collation.receiver.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import helper.kafka.service.KafkaHelper;
import helper.kafka.service.impl.KafkaHelperImpl;
import kafka.serializer.StringDecoder;

@Configuration
@PropertySource({"classpath:kafka.properties", "classpath:spark.properties"})
public class KafkaContext {
  
	@Value("${kafka.topics}")
	private String topics;
	
	@Value("${kafka.listener}")
	private String metaDataBrokerList;
	
	@Value("${spark.appname}")
	private String appName;
	
	@Value("${kafka.zookeepers}")
	private String zookeepers;

	
	@Bean
	public  Map<String, String> kafkaConfig(){
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", metaDataBrokerList); 
		return kafkaParams;
	}
	
	@Bean
	public JavaPairInputDStream<String, String> kafkaDStream(){
		Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
		// Create direct kafka stream with brokers and topics
		return KafkaUtils.createDirectStream(javaStreamingContext(), String.class, String.class,
				StringDecoder.class, StringDecoder.class, kafkaConfig(), topicsSet);

	}
	
	@Bean
	public JavaStreamingContext javaStreamingContext(){
		SparkConf sparkConf = new SparkConf().setAppName(appName);

		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		return new JavaStreamingContext(sc, new Duration(5000));
	}
	

	@Bean
	public Properties kafkaPropetiers() {
		Properties kafkaProps = new Properties();
		kafkaProps.put("metadata.broker.list", metaDataBrokerList);
		kafkaProps.put("bootstrap.servers", metaDataBrokerList);

		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("acks", "1");

		kafkaProps.put("retries", "1");
		kafkaProps.put("linger.ms", 5);
		return kafkaProps;
	}
	@Bean
	public KafkaHelper kafkaHelper() throws Exception{
		return new KafkaHelperImpl(kafkaPropetiers(), zookeepers, topics);
	}
}
