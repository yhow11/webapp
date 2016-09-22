package service.metricmanagement;

public interface MetricProcessor<T> {
	public void process(T param)  throws Exception;
}
