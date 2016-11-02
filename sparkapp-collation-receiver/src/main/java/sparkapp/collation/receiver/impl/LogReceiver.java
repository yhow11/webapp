package sparkapp.collation.receiver.impl;

import javax.annotation.Resource;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("LogReceiver")
@Transactional
public class LogReceiver implements VoidFunction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7595071060723304463L;
	@Resource
	private JavaPairInputDStream<String, String> streamer;
	@Resource(name="${LogReceiver.processor}")
	private VoidFunction<JavaRDD<String>> processor;
	@Resource
	private JavaStreamingContext streamingContext;
	
	public LogReceiver(){
		
	}
	@Override
	public void call(Object t) throws Exception {
		// TODO Auto-generated method stub
		JavaDStream<String> logs = streamer.map(line -> {
			System.out.println(line._2());
			return line._2();
		});
		
		logs.foreachRDD(processor);
		
		streamingContext.start();
		streamingContext.awaitTermination();
	}
}
