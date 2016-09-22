package service.metricmanagement.pagecount.param;

public class PageCountMetricParam {

	protected String visitorID;
	protected String type;
	protected String url;
	
	public PageCountMetricParam(String type, String url, String visitorID) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.url = url;
		this.visitorID = visitorID;
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
}
