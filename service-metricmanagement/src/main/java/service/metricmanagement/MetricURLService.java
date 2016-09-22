package service.metricmanagement;

import java.util.List;

import service.metricmanagement.enums.MetricTypeEnum;
import service.urlmanagement.model.URLMetricModel;

public interface MetricURLService {
	public List<URLMetricModel> getAll(String url, MetricTypeEnum metricType) throws Exception;
}
