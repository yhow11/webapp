package service.metricmanagement.timeonpage.param;

public class TimeOnPageMetricParam {

	protected String visitorID;
	protected String type;
	protected String url;
	protected String elapsedTime;

	public TimeOnPageMetricParam(String type, String url, String visitorID, String elapsedTime) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.url = url;
		this.visitorID = visitorID;
		this.elapsedTime = elapsedTime;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getVisitorID() {
		return visitorID;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

}
