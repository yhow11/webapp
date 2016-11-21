package sparkapp.collation.receiver.service.impl;

import javax.annotation.Resource;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sparkapp.collation.receiver.service.LogReceiver;
import usertracker.browser.visitorlog.model.VisitorLogModel;

@Service("LogReceiverImpl")
@Transactional
public class LogReceiverImpl implements LogReceiver {

	@Resource(name="${LogReceiver.mapper}")
	private Function<String, VisitorLogModel> mapper;
	@Resource(name="${LogReceiver.function}")
	private VoidFunction<JavaRDD<VisitorLogModel>> function;
	@Resource
	private JavaPairInputDStream<String, String> streamer;
	@Resource
	private JavaStreamingContext streamingContext;
	
	
	public LogReceiverImpl(){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			JavaDStream<VisitorLogModel> logs = streamer.map(line -> {
				System.out.println(line._2());
				return line._2();
			}).map(mapper);
			
			logs.foreachRDD(function);
			
			streamingContext.start();
			streamingContext.awaitTermination();
		} catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
