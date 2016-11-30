package usertracker.browser.visitor.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="activeVisitorModel")
public class ActiveVisitorModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String id;
	@PhoenixColumn(type="BIGINT")
	private Long timestamp;
	@PhoenixColumn(type="VARCHAR(255)")
	private String metrics;
	@PhoenixColumn(type="VARCHAR(400)")
	private String activities;
	@PhoenixColumn(type="VARCHAR(255)")
	private String location;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	public String getMetrics() {
		return metrics;
	}
	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
