package com.nurtureretargeting.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import helper.kafka.service.KafkaHelper;
import helper.kafka.service.impl.KafkaHelperImpl;


@Configuration
@PropertySource({"classpath:kafka.properties"})
public class KafkaContext {

	@Value("${kafka.topics}")
	private String topics;
	
	@Value("${kafka.listener}")
	private String metaDataBrokerList;
	
	@Value("${kafka.zookeepers}")
	private String zookeepers;
	
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
	public KafkaHelper kafkaHelper() {
		return new KafkaHelperImpl(kafkaPropetiers(), zookeepers, topics);
	}
}
