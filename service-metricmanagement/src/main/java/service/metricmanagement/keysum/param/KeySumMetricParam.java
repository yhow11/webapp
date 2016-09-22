package service.metricmanagement.keysum.param;

public class KeySumMetricParam {

	protected String visitorID;
	protected String type;
	protected String url;
	
	public KeySumMetricParam(String visitorID, String type, String url) {
		// TODO Auto-generated constructor stub
		this.visitorID = visitorID;
		this.type = type;
		this.url = url;
	}
	public String getVisitorID() {
		return visitorID;
	}
	public String getType() {
		return type;
	}
	public String getUrl() {
		return url;
	}
}
