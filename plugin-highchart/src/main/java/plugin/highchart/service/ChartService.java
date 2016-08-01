package plugin.highchart.service;

import java.util.List;

import plugin.highchart.object.Series;

public interface ChartService {

	public List<Series> getMonthlyCountOfCompletion(String type, String value);
}
