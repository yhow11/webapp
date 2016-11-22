package service.metricmanagement.metrics;

import java.util.List;

import common.Processor;
import service.metricmanagement.metrics.param.MetricProcessorParam;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

public interface MetricProcessor extends Processor<List<MetricProcessorParam>, List<MetricSummaryModel>>{

}
