package com.nurtureretargeting.tracker.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Queue;
import helper.kafka.service.KafkaHelper;

@Service("LogService")
@Transactional
public class LogService implements Queue<String> {
	
	@Autowired
	private KafkaHelper kafkaHelper;
	private String topic;
	private boolean sync;
	
	public LogService() {
		this.topic = "events";
		this.sync = true;
	}

	@Override
	public void send(String log) throws Exception {
		// TODO Auto-generated method stub
		kafkaHelper.send(log, topic, sync);
	}
	

}
