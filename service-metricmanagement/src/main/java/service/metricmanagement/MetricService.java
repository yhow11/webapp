package service.metricmanagement;

import java.util.List;

import common.service.JService;
import service.metricmanagement.model.MetricModel;

public interface MetricService  extends JService<MetricModel, MetricModel> {
	public List<MetricModel> getAll(Long offset, Long limit) throws Exception;
	public List<MetricModel> getAll(String key) throws Exception;
}
