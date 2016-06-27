package com.fingerprint.config;

import java.util.Properties;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:com/fingerprint/properties/application.properties",
	"classpath:com/fingerprint/properties/kafka.properties"})
public class ConfigContext {

	@Value("${brokerList}")
	private String brokerList;

	@Bean
	public org.apache.hadoop.conf.Configuration hbaseConfig() {
		System.setProperty("hadoop.home.dir", "C:\\winutils\\");
		org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
		// config.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
		// config.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
		config.set("hbase.zookeeper.quorum", "master,slave1");
		// config.set("hbase.zookeeper.property.maxClientCnxns", "1000");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		// config.set("hbase.client.scanner.timeout.period", "90000");
		// config.set("hbase.master", "103.253.145.213:16000");
		config.set("zookeeper.znode.parent", "/hbase-unsecure");
		return config;
	}

	@Bean
	public Properties kafkaPropetiers() {
		Properties kafkaProps = new Properties();
		kafkaProps.put("metadata.broker.list", brokerList);
		kafkaProps.put("bootstrap.servers", brokerList);

		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("acks", "1");

		kafkaProps.put("retries", "1");
		kafkaProps.put("linger.ms", 5);
		return kafkaProps;
	}
}
