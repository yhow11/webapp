package sparkapp.collation.receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import sparkapp.collation.receiver.config.AppContext;
import sparkapp.collation.receiver.config.DaoConfig;
import sparkapp.collation.receiver.config.KafkaContext;
import sparkapp.collation.receiver.config.MapperConfig;
import sparkapp.collation.receiver.config.PhoenixContext;
import sparkapp.collation.receiver.config.ServiceConfig;
import sparkapp.collation.receiver.config.StartUpContext;
import sparkapp.collation.receiver.service.ReceiverService;
import usertracker.base.UserParam;
import usertracker.browser.mapper.impl.VisitorLogStringMapper;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class, DaoConfig.class,
				ServiceConfig.class, MapperConfig.class, PhoenixContext.class, KafkaContext.class, StartUpContext.class);
		
		RestTemplate rt = (RestTemplate) ctx.getBean("restTemplateService");
		ReceiverService receiverService = (ReceiverService) ctx.getBean("receiverService");
		VisitorLogStringMapper visitorLogStringMapper = (VisitorLogStringMapper) ctx.getBean("visitorLogStringMapper");

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
				BrowserFPModel browserFP = receiverService.getOrCreateBrowserFP(visitorLogModel.getWebFP(), av.getId());
				DeviceFPModel deviceFP = receiverService.getOrCreateDeviceFP(visitorLogModel.getDeviceFP(), av.getId());

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
