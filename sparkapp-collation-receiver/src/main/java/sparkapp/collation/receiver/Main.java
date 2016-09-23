package sparkapp.collation.receiver;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import common.URLUtil;
import service.metricmanagement.MetricProcessor;
import service.metricmanagement.keysum.impl.KeySumMetricProcessorImpl;
import service.metricmanagement.keysum.param.KeySumMetricParam;
import service.metricmanagement.pagecount.impl.PageCountProcessorImpl;
import service.metricmanagement.pagecount.param.PageCountMetricParam;
import service.metricmanagement.timeonpage.impl.TimeOnPageProcessor;
import service.metricmanagement.timeonpage.param.TimeOnPageMetricParam;
import sparkapp.collation.receiver.config.AppContext;
import sparkapp.collation.receiver.config.DaoConfig;
import sparkapp.collation.receiver.config.KafkaContext;
import sparkapp.collation.receiver.config.ManagerConfig;
import sparkapp.collation.receiver.config.MapperConfig;
import sparkapp.collation.receiver.config.PhoenixContext;
import sparkapp.collation.receiver.config.ProcessorConfig;
import sparkapp.collation.receiver.config.ServiceConfig;
import sparkapp.collation.receiver.config.SparkConfig;
import sparkapp.collation.receiver.config.StartUpContext;
import sparkapp.collation.receiver.mapper.WebEventVisitorLogMapper;
import sparkapp.collation.receiver.service.ReceiverService;
import usertracker.base.UserParam;
import usertracker.browser.mapper.impl.VisitorLogStringMapper;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class, DaoConfig.class,
				ServiceConfig.class, MapperConfig.class, PhoenixContext.class, KafkaContext.class, SparkConfig.class, ManagerConfig.class, ProcessorConfig.class, StartUpContext.class);
		MetricProcessor<PageCountMetricParam> pageCountProcessor = ctx.getBean(PageCountProcessorImpl.class);
		MetricProcessor<TimeOnPageMetricParam> timeOnPageProcessor = ctx.getBean(TimeOnPageProcessor.class);
		MetricProcessor<KeySumMetricParam> keysumProcessor = ctx.getBean(KeySumMetricProcessorImpl.class);
		
		RestTemplate rt = ctx.getBean(RestTemplate.class);
		ReceiverService receiverService = ctx.getBean(ReceiverService.class);
		VisitorLogStringMapper visitorLogStringMapper = ctx.getBean(VisitorLogStringMapper.class);

//		List<String> columnList = new ArrayList<String>();
//		columnList.add("tKey");
//		columnList.add("tValues");
//		Seq<String> columnSeq = scala.collection.JavaConversions.asScalaBuffer(columnList);
//		Option<String> predicate = Option.apply("");
//		Option<String> zkURL = Option.apply("poc:2181");
//		
//		DataFrame df = sparkPhoenixSQL.phoenixTableAsDataFrame("keyTable", columnSeq, predicate, zkURL, new Configuration());
//		df.show();
	
//		Encoder<KeyModel> encoder = Encoders.bean(KeyModel.class);
//		Dataset<KeyModel> dataset = df.as(encoder);
//		dataset.show();
//		for(KeyModel key: dataset.collectAsList()){
//			System.out.println(key.gettKey());
//			System.out.println(key.gettValues());
//		}
		
//		Map<String, String> param = new HashMap<String, String>();
//		param.put("zkURL", "poc:2181");
//		param.put("table", "pageCountTable");
//		DataFrame pageCountDF = sQLContext.read().format("org.apache.phoenix.spark").options(param).load();
//		pageCountDF.registerTempTable("pageCountTable");
//		sQLContext.sql("SELECT * FROM pageCountTemp").show();
//		sQLContext.dropTempTable("pageCountTable");
		
		
		
		
		JavaStreamingContext jssc = (JavaStreamingContext)  ctx.getBean("javaStreamingContext");

		JavaPairInputDStream<String, String> messages = (JavaPairInputDStream<String, String>)  ctx.getBean("kafkaDStream");

		// Get the lines, split them into words, count the words and print
		JavaDStream<String> lines = messages.map(line -> {
			return line._2();
		});
		//
		lines.foreachRDD(items -> {

			JavaRDD<VisitorLogModel> rowRDD = (JavaRDD) items.map(item -> {
				return visitorLogStringMapper.map(item);
			});

			List<VisitorLogModel> visitorLogModels = rowRDD.collect();
			List<WebEventModel> webEventModels = new ArrayList<>();
			for (VisitorLogModel visitorLogModel : visitorLogModels) {
				
				receiverService.save(visitorLogModel);

				AnonymousVisitorModel visitor = receiverService.getOrCreateAV(visitorLogModel.getSessionID(), visitorLogModel.getWebFP());
				receiverService.getOrCreateSession(visitorLogModel.getSessionID(), visitor.getId());
				receiverService.getOrCreateBrowserFP(visitorLogModel.getWebFP(), visitor.getId());
				receiverService.getOrCreateDeviceFP(visitorLogModel.getDeviceFP(), visitor.getId());

				
				WebEventModel webEvent = new WebEventVisitorLogMapper(visitor.getId()).marshall(visitorLogModel);
				webEventModels.add(webEvent);
				receiverService.save(webEvent);
				System.out.println(URLUtil.getRealURL(webEvent.getUrl()));
				pageCountProcessor.process(new PageCountMetricParam(webEvent.getType(), webEvent.getUrl(), visitor.getId()));
				timeOnPageProcessor.process(new TimeOnPageMetricParam(webEvent.getType(), webEvent.getUrl(), visitor.getId(), webEvent.getElapsedTime()));
				keysumProcessor.process(new KeySumMetricParam(webEvent.getType(), webEvent.getUrl(), visitor.getId()));


			}

			if (webEventModels.size() > 0 && isbroadcastEnabled(args)) {
				try {
					UserParam<WebEventModel> data = new UserParam<WebEventModel>();
					data.setType(WebEventModel.class.getSimpleName());
					data.getData().addAll(webEventModels);
					rt.postForObject("http://poc:8191/webapp-poc/logs/broadcast", data, String.class);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}

			if (visitorLogModels.size() > 0 && isbroadcastEnabled(args)) {
				try {
					UserParam<VisitorLogModel> data = new UserParam<VisitorLogModel>();
					data.setType(VisitorLogModel.class.getSimpleName());
					data.getData().addAll(visitorLogModels);
					rt.postForObject("http://poc:8191/webapp-poc/logs/broadcast", data, String.class);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}
		});
		jssc.start();
		jssc.awaitTermination();
	}
	

	public static boolean isbroadcastEnabled(String[] args) {
		if (args.length > 0) {
			return "true".equals(args[0]);
		} else {
			return false;
		}
	}
}
