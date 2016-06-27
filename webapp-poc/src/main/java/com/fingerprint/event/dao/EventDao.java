package com.fingerprint.event.dao;

import java.util.List;

import com.fingerprint.event.model.EventModel;

public interface EventDao {

	public List<EventModel> getAll (Class<EventModel> clazz, Integer limit, String startRow, String lastRow)  throws Exception ;
	   
	public List<EventModel> find(String word, String column) throws Exception;
}
