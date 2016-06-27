package com.inctool.chart.dao;

import com.mongodb.DBObject;

public interface ChartDao {

	public Iterable<DBObject> getMonthlyCountOfCompletion(String type, String value);
}
