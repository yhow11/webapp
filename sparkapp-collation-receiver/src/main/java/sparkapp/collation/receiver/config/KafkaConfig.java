package sparkapp.collation.receiver.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import helper.kafka.service.KafkaHelper;
import helper.kafka.service.impl.KafkaHelperImpl;
import helper.spring.ZookeeperContext;
import kafka.serializer.StringDecoder;

@Configuration
@PropertySource({"classpath:kafka.properties"})
public class KafkaConfig {
  
	@Value("${kafka.topics}")
	private String topics;
	
	@Value("${kafka.listener}")
	private String listeners;
	
	@Autowired
	private ZookeeperContext zookeeperContext;

	@Autowired
	private SparkConfig sparkContext;
	
	
	private  Map<String, String> kafkaConfig(){
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", listeners); 
		return kafkaParams;
	}
	private Properties kafkaPropetiers() {
		Properties kafkaProps = new Properties();
		kafkaProps.put("metadata.broker.list", listeners);
		kafkaProps.put("bootstrap.servers", listeners);

		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("acks", "1");

		kafkaProps.put("retries", "1");
		kafkaProps.put("linger.ms", 5);
		return kafkaProps;
	}
	
	
	@Bean
	public JavaPairInputDStream<String, String> kafkaDStream(){
		Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
		// Create direct kafka stream with brokers and topics
		return KafkaUtils.createDirectStream(sparkContext.javaStreamingContext(), String.class, String.class,
				StringDecoder.class, StringDecoder.class, kafkaConfig(), topicsSet);

	}
	@Bean
	public KafkaHelper kafkaHelper() throws Exception{
		return new KafkaHelperImpl(kafkaPropetiers(), zookeeperContext.getZookeepers(), topics);
	}
}
