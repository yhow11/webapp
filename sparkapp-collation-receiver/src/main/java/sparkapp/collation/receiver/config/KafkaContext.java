package sparkapp.collation.receiver.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kafka.serializer.StringDecoder;

@Configuration
public class KafkaContext {
  
	private static final String topics = "events";
	
	@Bean
	public  Map<String, String> kafkaConfig(){
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", "poc:6667"); 
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
		SparkConf sparkConf = new SparkConf().setAppName("CollationReceiver");

		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		return new JavaStreamingContext(sc, new Duration(5000));
	}
	
}
