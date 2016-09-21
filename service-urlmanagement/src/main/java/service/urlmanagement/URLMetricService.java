package service.urlmanagement;

import java.util.List;

import service.metricmanagement.enums.MetricTypeEnum;
import service.urlmanagement.model.URLMetricModel;

public interface URLMetricService {
	public List<URLMetricModel> getAll(String url, MetricTypeEnum metricType) throws Exception;
}
