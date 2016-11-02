package usertracker.browser.visitor.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActiveVisitorForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String timestamp;
	private String timeago;
	private List<String> metrics = new ArrayList<>();
	private List<String> activities = new ArrayList<>();
	private String location;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getMetrics() {
		return metrics;
	}
	public void setMetrics(List<String> metrics) {
		this.metrics = metrics;
	}
	public List<String> getActivities() {
		return activities;
	}
	public void setActivities(List<String> activities) {
		this.activities = activities;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTimeago() {
		return timeago;
	}
	public void setTimeago(String timeago) {
		this.timeago = timeago;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
