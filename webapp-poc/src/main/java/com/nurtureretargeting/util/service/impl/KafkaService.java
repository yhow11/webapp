package com.nurtureretargeting.util.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
 
public class KafkaService {
 
    private Producer<String, String> producer;
    
    private Properties properties;
 
    private String sync;
    
    private String topic;
    public KafkaService(Properties properties, String sync, String topic) {
    	this.properties = properties;
    	this.sync = sync;
    	this.topic = topic;
    }
 
    @PostConstruct
    public void initIt() {
 
        producer = new KafkaProducer<>(properties);
 
    }
 
    public void send(String value) throws ExecutionException, 
            InterruptedException {
        if ("sync".equalsIgnoreCase(sync)) {
            sendSync(value);
        } else {
            sendAsync(value);
        }
    }
 
    private void sendSync(String value) throws ExecutionException,
            InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);
        producer.send(record).get();
 
    }
 
    private void sendAsync(String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);
 
        producer.send(record, (RecordMetadata recordMetadata, Exception e) -> {
            if (e != null) {
                e.printStackTrace();
            }
        });
    }
}
