package com.fingerprint.event.dao.impl;

import java.util.List;

import com.fingerprint.base.dao.impl.BaseDaoImpl;
import com.fingerprint.event.dao.EventDao;
import com.fingerprint.event.model.EventModel;

import hbase.dao.HBaseDao;

public class EventDaoImpl extends BaseDaoImpl implements EventDao {

	public EventDaoImpl(HBaseDao hbaseDao) {
		// TODO Auto-generated constructor stub
		super(hbaseDao);
	}
	@Override
	public List<EventModel> find(String word, String column) throws Exception {
		// TODO Auto-generated method stub
		return hbaseDao.find(word, "metadata", column, EventModel.class);
	}
	@Override
	public List<EventModel> getAll(Class<EventModel> clazz, Integer limit, String startRow, String lastRow) throws Exception {
		// TODO Auto-generated method stub
		return hbaseDao.getAll(EventModel.class, limit, startRow, lastRow);
	}
	
	

}
