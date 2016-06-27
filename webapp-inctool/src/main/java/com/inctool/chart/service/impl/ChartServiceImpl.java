package com.inctool.chart.service.impl;

import java.util.List;

import com.inctool.chart.dao.ChartDao;
import com.inctool.chart.form.SeriesForm;
import com.inctool.chart.mapper.ChartMapper;
import com.inctool.chart.service.ChartService;
import com.mongodb.DBObject;

public class ChartServiceImpl implements ChartService {


	private ChartDao chartDao;
	private ChartMapper chartMapper;
	
	public ChartServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	public ChartServiceImpl(ChartDao chartDao, ChartMapper chartMapper) {
		// TODO Auto-generated constructor stub
		this.chartDao = chartDao;
		this.chartMapper = chartMapper;
	}
	
	@Override
	public List<SeriesForm> getMonthlyCountOfCompletion(String type, String value) {
		// TODO Auto-generated method stub
		return chartMapper.map(chartDao.getMonthlyCountOfCompletion(type, value));
	}

}
