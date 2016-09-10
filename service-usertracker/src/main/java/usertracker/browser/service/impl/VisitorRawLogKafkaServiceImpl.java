package usertracker.browser.service.impl;

import helper.kafka.service.KafkaHelper;
import usertracker.browser.service.VisitorRawLogService;

public class VisitorRawLogKafkaServiceImpl implements VisitorRawLogService {

	private KafkaHelper kafkaHelper;
	private String topic;
	private boolean sync;
	
	public VisitorRawLogKafkaServiceImpl(KafkaHelper kafkaHelper, String topic, boolean sync) {
		this.kafkaHelper = kafkaHelper;
		this.topic = topic;
		this.sync = sync;
	}

	@Override
	public void save(String log) throws Exception {
		// TODO Auto-generated method stub
		kafkaHelper.send(log, topic, sync);
	}
	
	
}
