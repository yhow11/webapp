package service.metricmanagement;

import java.util.List;

import common.service.JService;
import service.metricmanagement.model.MetricSummaryModel;

public interface MetricSummaryService  extends JService<MetricSummaryModel, MetricSummaryModel> {
	public List<MetricSummaryModel> getAll(String type, Long offset, Long limit) throws Exception;
}
