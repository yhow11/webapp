package service.metricmanagement.timeonpage;

import common.query.QueryParam;
import common.service.JService;
import service.metricmanagement.timeonpage.model.TimeOnPageMetricModel;

public interface TimeOnPageMetricService extends JService<TimeOnPageMetricModel, TimeOnPageMetricModel> {
	public TimeOnPageMetricModel get(QueryParam<TimeOnPageMetricModel> param) throws Exception;
	public TimeOnPageMetricModel getHighest(String visitorID, String metric) throws Exception;
}
