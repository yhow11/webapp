package com.inctool.chart.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.inctool.chart.form.SeriesForm;
import com.inctool.chart.mapper.ChartMapper;
import com.mongodb.DBObject;

public class ChartMapperImpl implements ChartMapper {

	@Override
	public List<SeriesForm> map(Iterable<DBObject> iterables) {
		// TODO Auto-generated method stub
		List<SeriesForm> results = new ArrayList<>();
		for(DBObject item: iterables) {
			SeriesForm serie = new SeriesForm();
			serie.setName((String) item.get("id"));
			List<?> data = (List<?>) item.get("data");
			serie.setData(data);;
			results.add(serie);
		}
		return results;
	}

}
