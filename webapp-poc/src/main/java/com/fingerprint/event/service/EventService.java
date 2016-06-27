package com.fingerprint.event.service;

import java.util.List;

import com.fingerprint.event.model.EventModel;

public interface EventService {
	public List<EventModel> getAll(Integer limit)  throws Exception;
	public List<EventModel> getAll()  throws Exception;
	public List<EventModel> getAll(Integer limit, String startRow, String lastRow)  throws Exception;
	public List<EventModel> find(String word, String column) throws Exception;
}
