package sparkapp.collation.receiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.spark.sql.functions.col;
import org.apache.hadoop.conf.Configuration;
import org.apache.phoenix.spark.SparkSqlContextFunctions;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import scala.Option;
import scala.collection.Seq;
import service.keymanagement.model.KeyModel;
import service.pagecount.model.PageCountModel;
import sparkapp.collation.receiver.config.AppContext;
import sparkapp.collation.receiver.config.DaoConfig;
import sparkapp.collation.receiver.config.KafkaContext;
import sparkapp.collation.receiver.config.ManagerConfig;
import sparkapp.collation.receiver.config.MapperConfig;
import sparkapp.collation.receiver.config.PhoenixContext;
import sparkapp.collation.receiver.config.ServiceConfig;
import sparkapp.collation.receiver.config.SparkContext;
import sparkapp.collation.receiver.config.StartUpContext;
import sparkapp.collation.receiver.manager.PartialPageCountManager;
import sparkapp.collation.receiver.mapper.WebEventVisitorLogMapper;
import sparkapp.collation.receiver.object.PartialPageCount;
import sparkapp.collation.receiver.service.ReceiverService;
import usertracker.base.UserParam;
import usertracker.browser.mapper.impl.VisitorLogStringMapper;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class, DaoConfig.class,
				ServiceConfig.class, MapperConfig.class, PhoenixContext.class, KafkaContext.class, SparkContext.class, ManagerConfig.class, StartUpContext.class);
		SQLContext sQLContext = (SQLContext) ctx.getBean("sQLContext");
		SparkSqlContextFunctions sparkPhoenixSQL = (SparkSqlContextFunctions) ctx.getBean("sparkSqlContextFunctions");
		RestTemplate rt = (RestTemplate) ctx.getBean("restTemplateService");
		PartialPageCountManager partialPageCountManager = (PartialPageCountManager) ctx.getBean("partialPageCountManager");
		ReceiverService receiverService = (ReceiverService) ctx.getBean("receiverService");
		VisitorLogStringMapper visitorLogStringMapper = (VisitorLogStringMapper) ctx.getBean("visitorLogStringMapper");

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
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("zkURL", "poc:2181");
		param.put("table", "pageCountTable");
		DataFrame pageCountDF = sQLContext.read().format("org.apache.phoenix.spark").options(param).load();
		pageCountDF.registerTempTable("pageCountTemp");
		sQLContext.sql("SELECT * FROM pageCountTemp").show();
//		
//		List<PageCountModel> pageCounts = new ArrayList<>();
//		for(PartialPageCount partialPageCount: partialPageCountManager.getAll("")){
//			PageCountModel pageCountModel = new PageCountModel();
//			pageCountModel.setVISITORID("test");
//			pageCountModel.setTKEY(partialPageCount.getTKEY());
//			pageCountModel.setMETRIC(partialPageCount.getMETRIC());
//			pageCountModel.setURL(partialPageCount.getURL());
//			pageCountModel.setTVALUES(partialPageCount.getTVALUES());
//			pageCountModel.setTCOUNT(1L);
//			
//			param.put("table", "pageCountTable");
//			DataFrame pageCountDF = sQLContext.read().format("org.apache.phoenix.spark").options(param).load();
//			pageCountDF = pageCountDF.filter(
//					col("VISITORID").equalTo(pageCountModel.getVISITORID())
//					.and(col("TKEY").equalTo(pageCountModel.getTKEY()))
//					.and(col("METRIC").equalTo(pageCountModel.getMETRIC()))
//					.and(col("URL").equalTo(pageCountModel.getURL()))
//					.and(col("TVALUES").equalTo(pageCountModel.getTVALUES()))
//			);
//			Long count = pageCountDF.count();
//			if(count > 0){
//				pageCountDF = pageCountDF.select(
//						col("VISITORID"), 
//						col("TKEY"), 
//						col("METRIC"), 
//						col("URL"), 
//						col("TVALUES"), 
//						col("TCOUNT").plus(1).alias("TCOUNT")
//				);
//				pageCountDF.show();
//				pageCountDF.write().format("org.apache.phoenix.spark").mode(SaveMode.Overwrite).options(param).save();
//			} else {
//				pageCounts.add(pageCountModel);
//			}
//		}
//		
//		if(pageCounts.size() > 0) {
//			DataFrame pageCountDF = sQLContext.createDataFrame(pageCounts, PageCountModel.class);
//			param.put("table", "pageCountTable");
//			pageCountDF.write().format("org.apache.phoenix.spark").mode(SaveMode.Overwrite).options(param).save();
//		}
		
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
				
				System.out.println("Saving..");
				receiverService.save(visitorLogModel);

				AnonymousVisitorModel av = receiverService.getOrCreateAV(visitorLogModel.getSessionID(), visitorLogModel.getWebFP());
				receiverService.getOrCreateSession(visitorLogModel.getSessionID(), av.getId());
				receiverService.getOrCreateBrowserFP(visitorLogModel.getWebFP(), av.getId());
				receiverService.getOrCreateDeviceFP(visitorLogModel.getDeviceFP(), av.getId());

				
				WebEventModel webEvent = new WebEventVisitorLogMapper(av.getId()).marshall(visitorLogModel);
				webEventModels.add(webEvent);
				receiverService.save(webEvent);
				
				
				System.out.println("Created New WebEvent " + webEvent.getId());

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
