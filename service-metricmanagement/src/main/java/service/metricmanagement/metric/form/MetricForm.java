package service.metricmanagement.metric.form;

import java.util.ArrayList;
import java.util.List;

import service.keymanagement.form.KeyForm;
import service.metricmanagement.metrictype.form.MetricTypeForm;


public class MetricForm {

	private String id;
	private String name;
	private List<MetricTypeForm> types = new ArrayList<>();
	private List<KeyForm> keys = new ArrayList<>();
	
	
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

	public List<MetricTypeForm> getTypes() {
		return types;
	}

	public void setTypes(List<MetricTypeForm> types) {
		this.types = types;
	}

	public List<KeyForm> getKeys() {
		return keys;
	}

	public void setKeys(List<KeyForm> keys) {
		this.keys = keys;
	}

	
}
