package sparkapp.collation.receiver.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.phoenix.spark.PhoenixRDD;
import org.apache.phoenix.spark.SparkContextFunctions;
import org.apache.phoenix.spark.SparkSqlContextFunctions;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@Value("${kafka.zookeepers}")
	private String zookeepers;

	@Autowired
	private SparkContext sparkContext;
	
	
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
		return KafkaUtils.createDirectStream(sparkContext.javaStreamingContext(), String.class, String.class,
				StringDecoder.class, StringDecoder.class, kafkaConfig(), topicsSet);

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
