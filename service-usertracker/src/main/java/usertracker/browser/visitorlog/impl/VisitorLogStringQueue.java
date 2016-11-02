package usertracker.browser.visitorlog.impl;

import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Queue;
import helper.kafka.service.KafkaHelper;

@Transactional
public class VisitorLogStringQueue implements Queue<String> {

	private KafkaHelper kafkaHelper;
	private String topic;
	private boolean sync;
	
	public VisitorLogStringQueue(KafkaHelper kafkaHelper) {
		this.kafkaHelper = kafkaHelper;
		this.topic = "events";
		this.sync = true;
	}

	@Override
	public void send(String log) throws Exception {
		// TODO Auto-generated method stub
		kafkaHelper.send(log, topic, sync);
	}
	
	
}
