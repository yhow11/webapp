package plugin.highchart.service.impl;

import java.util.List;

import plugin.highchart.dao.ChartDao;
import plugin.highchart.object.Series;
import plugin.highchart.service.ChartService;

public class ChartServiceImpl implements ChartService {


	private ChartDao chartDao;
	
	public ChartServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	public ChartServiceImpl(ChartDao chartDao) {
		// TODO Auto-generated constructor stub
		this.chartDao = chartDao;
	}
	
	@Override
	public List<Series> getMonthlyCountOfCompletion(String type, String value) {
		// TODO Auto-generated method stub
		return chartDao.getCompletion(Series.class, "", type, value);
	}

}
