package helper.kafka.service;

import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;

public abstract class KafkaService {

	protected ZkClient zkClient;
	
	public KafkaService(String zookeepers, String topics){
		zkClient = new ZkClient(zookeepers, 10000, 10000, ZKStringSerializer$.MODULE$);
		createTopic(topics);
	}
	
	public void createTopic(String topics){
		for (String topic : topics.split(",")) {
			System.out.println("Topic exists? "+ AdminUtils.topicExists(zkClient, topic));
			if (!AdminUtils.topicExists(zkClient, topic)) {
				AdminUtils.createTopic(zkClient, topic, 1, 1, new Properties());
			}

		}
	}
	
	
}
