package helper.kafka.service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;

public abstract class KafkaHelper {

	protected Producer<String, String> producer;
	protected ZkClient zkClient;

	public KafkaHelper(Properties properties, String zookeepers, String topics) {
		zkClient = new ZkClient(zookeepers, 10000, 10000, ZKStringSerializer$.MODULE$);
		producer = new KafkaProducer<>(properties);
		createTopic(topics);
	}

	public void createTopic(String topics) {
		for (String topic : topics.split(",")) {
			System.out.println("Topic exists? " + AdminUtils.topicExists(zkClient, topic));
			if (!AdminUtils.topicExists(zkClient, topic)) {
				AdminUtils.createTopic(zkClient, topic, 1, 1, new Properties());
			}

		}
	}

	public void send(String value, String topic, boolean sync) throws ExecutionException, InterruptedException {
		if (sync) {
			sendSync(value, topic);
		} else {
			sendAsync(value, topic);
		}
	}

	private void sendSync(String value, String topic) throws ExecutionException, InterruptedException {
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);
		producer.send(record).get();

	}

	private void sendAsync(String value, String topic) {
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);

		producer.send(record, (RecordMetadata recordMetadata, Exception e) -> {
			if (e != null) {
				e.printStackTrace();
			}
		});
	}
}
