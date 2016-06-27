package com.fingerprint.event.service.impl;

import java.util.List;

import com.fingerprint.event.dao.EventDao;
import com.fingerprint.event.model.EventModel;
import com.fingerprint.event.service.EventService;

public class EventServiceImpl implements EventService {
	
	private EventDao eventDao;
	
	public EventServiceImpl(EventDao eventDao) {
		// TODO Auto-generated constructor stub
		this.eventDao = eventDao;
	}

	@Override
	public List<EventModel> getAll() throws Exception {
		// TODO Auto-generated method stub
		return eventDao.getAll(EventModel.class, null, null, null);
	}

	@Override
	public List<EventModel> find(String word, String column) throws Exception {
		// TODO Auto-generated method stub
		return eventDao.find(word, column);
	}

	@Override
	public List<EventModel> getAll(Integer limit, String startRow, String lastRow)
			throws Exception {
		// TODO Auto-generated method stub
		return eventDao.getAll(EventModel.class, limit, startRow, lastRow);
	}

	@Override
	public List<EventModel> getAll(Integer limit) throws Exception {
		// TODO Auto-generated method stub
		return eventDao.getAll(EventModel.class, limit, null, null);
	}

	
	
}
