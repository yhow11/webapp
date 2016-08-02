package sparkapp.collation.receiver;

import java.util.ArrayList;
import kafka.utils.ZKStringSerializer$;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.I0Itec.zkclient.ZkClient;
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

import helper.kafka.service.KafkaService;
import kafka.admin.AdminUtils;
import kafka.serializer.StringDecoder;
import sparkapp.collation.receiver.config.ConfigContext;
import sparkapp.collation.receiver.config.DaoConfig;
import sparkapp.collation.receiver.config.KafkaContext;
import sparkapp.collation.receiver.config.MapperConfig;
import sparkapp.collation.receiver.config.PhoenixContext;
import sparkapp.collation.receiver.config.ServiceConfig;
import usertracker.base.UserParam;
import usertracker.browser.mapper.impl.VisitorLogStringMapper;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.VisitorLogService;

public class Main {

	public static void main(String[] args) throws Exception {

		Logger.getLogger("sparkapp.collation.receiver.Main").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.dstream.DStream").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.dstream.WindowedDStream").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.DStreamGraph").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.scheduler.JobGenerator").setLevel(Level.INFO);

		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigContext.class, DaoConfig.class,
				ServiceConfig.class, MapperConfig.class, PhoenixContext.class, KafkaContext.class);
		
		VisitorLogService visitorLogService = (VisitorLogService) ctx.getBean("visitorLogService");
		VisitorLogStringMapper visitorLogStringMapper = (VisitorLogStringMapper) ctx.getBean("visitorLogStringMapper");

		JavaStreamingContext jssc = (JavaStreamingContext)  ctx.getBean("javaStreamingContext");

		JavaPairInputDStream<String, String> messages = (JavaPairInputDStream<String, String>)  ctx.getBean("kafkaDStream");

		if (isCreateNewTableEnabled(args)) {
			visitorLogService.creatTable(VisitorLogModel.class);
			visitorLogService.creatTable(AnonymousVisitorModel.class);
			visitorLogService.creatTable(BrowserFPModel.class);
			visitorLogService.creatTable(DeviceFPModel.class);
			visitorLogService.creatTable(SessionModel.class);
			visitorLogService.creatTable(WebEventModel.class);
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

			List<VisitorLogModel> visitorLogModels = rowRDD.collect();
			List<WebEventModel> webEventModels = new ArrayList<>();
			for (VisitorLogModel visitorLogModel : visitorLogModels) {
				
				System.out.println("Saving..");
				visitorLogModel.setId(UUID.randomUUID().toString());
				visitorLogService.save(VisitorLogModel.class, visitorLogModel);

				AnonymousVisitorModel av = null;
				BrowserFPModel browserFP = null;
				DeviceFPModel deviceFP = null;
				
				SessionModel sessionModel = visitorLogService.getOne(SessionModel.class,
						visitorLogModel.getSessionID());
				System.out.println(sessionModel);
				
				av = findAV(visitorLogService, sessionModel, visitorLogModel);

				if (sessionModel == null) {
					sessionModel = new SessionModel();
					sessionModel.setId(visitorLogModel.getSessionID());
					sessionModel.setAnonymousVisitorID(av.getId());
					visitorLogService.save(SessionModel.class, sessionModel);
					System.out.println("Created New Session " + sessionModel.getId());
				} else {
					System.out.println("Session Found. " + sessionModel.getId());
				}

				browserFP = findBrowserFP(visitorLogService, av, visitorLogModel);
				deviceFP = findDeviceFP(visitorLogService, av, visitorLogModel);

				WebEventModel webEvent = new WebEventModel();
				webEvent.setId(UUID.randomUUID().toString());
				webEvent.setAnonymousVisitorID(av.getId());
				webEvent.setBrowserFPID(browserFP.getId());
				webEvent.setDeviceFPID(deviceFP.getId());
				webEvent.setTimeStamp(visitorLogModel.getTimeStamp());
				webEvent.setTitle(visitorLogModel.getTitle());
				webEvent.setType(visitorLogModel.getType());
				webEvent.setUrl(visitorLogModel.getUrl());
				webEventModels.add(webEvent);
				visitorLogService.save(WebEventModel.class, webEvent);
				System.out.println("Created New WebEvent " + webEvent.getId());

			}

			
			
			if (webEventModels.size() > 0 && isbroadcastEnabled(args)) {
				try {
					UserParam<WebEventModel> data = new UserParam<WebEventModel>();
					data.setType(WebEventModel.class.getSimpleName());
					data.getData().addAll(webEventModels);
					broadcast("http://poc:8191/webapp-poc/notifyEvents", data);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}

			if (visitorLogModels.size() > 0 && isbroadcastEnabled(args)) {
				try {
					UserParam<VisitorLogModel> data = new UserParam<VisitorLogModel>();
					data.setType(VisitorLogModel.class.getSimpleName());
					data.getData().addAll(visitorLogModels);
					broadcast("http://poc:8191/webapp-poc/notifyEvents", data);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}
		});
		jssc.start();
		jssc.awaitTermination();
	}
	
	public static String broadcast(String url, Object data){
		RestTemplate rt = new RestTemplate();

		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		rt.getMessageConverters().add(new StringHttpMessageConverter());
		return rt.postForObject(url, data, String.class);
	}

	public static BrowserFPModel findBrowserFP(VisitorLogService visitorLogService, AnonymousVisitorModel av,
			VisitorLogModel visitorLogModel) throws Exception {
		BrowserFPModel browserFP = visitorLogService.getOne(BrowserFPModel.class, visitorLogModel.getWebFP());
		if (browserFP == null) {
			browserFP = new BrowserFPModel();
			browserFP.setAnonymousVisitorID(av.getId());
			browserFP.setId(visitorLogModel.getWebFP());
			visitorLogService.save(BrowserFPModel.class, browserFP);
			System.out.println("Created Browser FP " + browserFP.getId());
		} else {
			System.out.println("Browser FP Found. " + browserFP.getId());
		}
		return browserFP;
	}

	public static DeviceFPModel findDeviceFP(VisitorLogService visitorLogService, AnonymousVisitorModel av,
			VisitorLogModel visitorLogModel) throws Exception {
		DeviceFPModel deviceFP = visitorLogService.getOne(DeviceFPModel.class, visitorLogModel.getDeviceFP());
		if (deviceFP == null) {
			deviceFP = new DeviceFPModel();
			deviceFP.setAnonymousVisitorID(av.getId());
			deviceFP.setId(visitorLogModel.getDeviceFP());
			visitorLogService.save(DeviceFPModel.class, deviceFP);
			System.out.println("Created Device FP " + deviceFP.getId());
		} else {
			System.out.println("Device FP Found. " + deviceFP.getId());
		}
		return deviceFP;
	}

	public static AnonymousVisitorModel findAV(VisitorLogService visitorLogService, SessionModel sessionModel,
			VisitorLogModel visitorLogModel) throws Exception {
		AnonymousVisitorModel av = null;
		if (sessionModel != null) {
			System.out.println("Finding Anonymous Visitor by session..");
			av = visitorLogService.getOne(AnonymousVisitorModel.class, sessionModel.getAnonymousVisitorID());
			if (av != null) {
				System.out.println("Anonymous Vistor Found. " + av.getId());
				return av;
			}
		}
		if (av == null) {
			BrowserFPModel browserFP = visitorLogService.getOne(BrowserFPModel.class, visitorLogModel.getWebFP());
			if (browserFP != null) {
				System.out.println("Finding Anonymous Visitor by Browser FP..");
				av = visitorLogService.getOne(AnonymousVisitorModel.class, browserFP.getAnonymousVisitorID());
			} else {

				av = new AnonymousVisitorModel();
				av.setId(UUID.randomUUID().toString());
				visitorLogService.save(AnonymousVisitorModel.class, av);
				System.out.println("Created New Anonymous Visitor " + av.getId());

			}
		}
		return av;
	}

	public static boolean isCreateNewTableEnabled(String[] args, Class<?>... classes) {
		if (args.length > 0) {
			return "true".equals(args[0]);
		} else {
			return false;
		}
	}

	public static boolean isbroadcastEnabled(String[] args) {
		if (args.length > 1) {
			return "true".equals(args[1]);
		} else {
			return false;
		}
	}
}
