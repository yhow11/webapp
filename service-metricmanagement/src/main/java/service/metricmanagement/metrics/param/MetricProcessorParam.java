package service.metricmanagement.metrics.param;

public class MetricProcessorParam {
	
	protected String visitorID;
	protected String type;
	protected String url;
	protected String title;
	protected String elapsedTime;
	
	public MetricProcessorParam(String visitorID, String type,String url) {
		// TODO Auto-generated constructor stub
		this.visitorID = visitorID;
		this.type = type;
		this.url = url;
	}
	public MetricProcessorParam(String visitorID, String type,String url, String elapseTime) {
		// TODO Auto-generated constructor stub
		this(visitorID,type,url);
		this.elapsedTime = elapseTime;
	}
	public String getVisitorID() {
		return visitorID;
	}
	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	
}
