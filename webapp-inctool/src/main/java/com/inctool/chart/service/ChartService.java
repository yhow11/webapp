package com.inctool.chart.service;

import java.util.List;

import com.inctool.chart.form.SeriesForm;
import com.mongodb.DBObject;

public interface ChartService {

	public List<SeriesForm> getMonthlyCountOfCompletion(String type, String value);
}
