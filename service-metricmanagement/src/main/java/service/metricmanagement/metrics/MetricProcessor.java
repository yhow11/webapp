package service.metricmanagement.metrics;

import service.metricmanagement.metrics.param.MetricProcessorParam;

public interface MetricProcessor {

	public void process(MetricProcessorParam param);
}
