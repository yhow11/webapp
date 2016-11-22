package service.segment.processor.param;

public class VisitorSegmentorParam {

	private String visitorID;
	private String metricID;
	private String values;

	public VisitorSegmentorParam(String visitorID, String metricID, String values) {
		// TODO Auto-generated constructor stub
		this.visitorID = visitorID;
		this.metricID = metricID;
		this.values = values;
	}
	public String getVisitorID() {
		return visitorID;
	}

	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getMetricID() {
		return metricID;
	}
	public void setMetricID(String metricID) {
		this.metricID = metricID;
	}
}
