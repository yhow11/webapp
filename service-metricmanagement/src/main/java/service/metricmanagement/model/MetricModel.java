package service.metricmanagement.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="metricTable")
public class MetricModel {

	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	private Long id;
	@PhoenixColumn(type="VARCHAR(100)")
	private String name;
	@PhoenixColumn(type="VARCHAR(100)")
	private String type;
	@PhoenixColumn(type="VARCHAR(100)")
	private String tKey;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String gettKey() {
		return tKey;
	}
	public void settKey(String tKey) {
		this.tKey = tKey;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
