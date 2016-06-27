package com.fingerprint.util.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "eventCountModel")
public class EventCountModel {
		
	@Id
	private ObjectId id;
	private String webFP;
	private String deviceFP;
	private String event;
	private String text;
	private int count;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getWebFP() {
		return webFP;
	}
	public void setWebFP(String webFP) {
		this.webFP = webFP;
	}
	public String getDeviceFP() {
		return deviceFP;
	}
	public void setDeviceFP(String deviceFP) {
		this.deviceFP = deviceFP;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}