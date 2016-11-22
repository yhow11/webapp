package service.metricmanagement.metricurl.model;

public class MetricURLModel {
	private String TKEY;
	private String URL;
	private String TVALUES;
	private Long METRICID;
	private String METRIC;
	private String METRICTYPE;
	
	public String getTKEY() {
		return TKEY;
	}
	public void setTKEY(String tKEY) {
		TKEY = tKEY;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getTVALUES() {
		return TVALUES;
	}
	public void setTVALUES(String tVALUES) {
		TVALUES = tVALUES;
	}
	public String getMETRIC() {
		return METRIC;
	}
	public void setMETRIC(String mETRIC) {
		METRIC = mETRIC;
	}
	public String getMETRICTYPE() {
		return METRICTYPE;
	}
	public void setMETRICTYPE(String mETRICTYPE) {
		METRICTYPE = mETRICTYPE;
	}
	public Long getMETRICID() {
		return METRICID;
	}
	public void setMETRICID(Long mETRICID) {
		METRICID = mETRICID;
	}
}
