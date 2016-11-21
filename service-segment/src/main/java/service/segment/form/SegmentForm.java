package service.segment.form;

import java.util.Arrays;
import java.util.List;

import service.metricmanagement.metric.form.MetricForm;
import service.segment.filter.form.FilterForm;

public class SegmentForm {

	private String id;
	private String name;
	private List<MetricForm> metrics;
	private FilterForm filter;
	private String value;
	private List<String> actions = Arrays.asList(new String[]{"EDIT", "VIEW"});
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MetricForm> getMetrics() {
		return metrics;
	}
	public void setMetrics(List<MetricForm> metrics) {
		this.metrics = metrics;
	}
	public FilterForm getFilter() {
		return filter;
	}
	public void setFilter(FilterForm filter) {
		this.filter = filter;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<String> getActions() {
		return actions;
	}
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	
	
	
}
