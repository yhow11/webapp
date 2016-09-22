package service.metricmanagement.keysum;

import common.query.QueryParam;
import common.service.JService;
import service.metricmanagement.keysum.model.KeySumMetricModel;

public interface KeySumMetricService extends JService<KeySumMetricModel, KeySumMetricModel> {
	public KeySumMetricModel get(QueryParam<KeySumMetricModel> param) throws Exception;
	public Long getSum(String visitorID, String metric) throws Exception;
}
