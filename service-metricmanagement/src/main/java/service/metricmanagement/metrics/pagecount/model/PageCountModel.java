package service.metricmanagement.metrics.pagecount.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="pageCountTable")
public class PageCountModel {

	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String VISITORID;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String URL;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String METRIC;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String TKEY;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String TVALUES;
	@PhoenixColumn(type="BIGINT")
	private Long TCOUNT;
	public String getVISITORID() {
		return VISITORID;
	}
	public void setVISITORID(String vISITORID) {
		VISITORID = vISITORID;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getMETRIC() {
		return METRIC;
	}
	public void setMETRIC(String mETRIC) {
		METRIC = mETRIC;
	}
	public String getTKEY() {
		return TKEY;
	}
	public void setTKEY(String tKEY) {
		TKEY = tKEY;
	}
	public String getTVALUES() {
		return TVALUES;
	}
	public void setTVALUES(String tVALUES) {
		TVALUES = tVALUES;
	}
	public Long getTCOUNT() {
		return TCOUNT;
	}
	public void setTCOUNT(Long tCOUNT) {
		TCOUNT = tCOUNT;
	}
	
	
}
