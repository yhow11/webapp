package com.inctool.chart.mapper;

import java.util.List;

import com.inctool.chart.form.SeriesForm;
import com.mongodb.DBObject;

public interface ChartMapper {

	public List<SeriesForm> map(Iterable<DBObject> iterables);
}
