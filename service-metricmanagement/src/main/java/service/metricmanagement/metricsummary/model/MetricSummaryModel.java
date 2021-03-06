package service.metricmanagement.metricsummary.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="metricSummaryTable")
public class MetricSummaryModel {

	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String VISITORID;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String METRICID;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String METRICNAME;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String METRICTYPE;
	@PhoenixColumn(type="VARCHAR(255)")
	private String TVALUES;
	
	
	public String getMETRICID() {
		return METRICID;
	}
	public void setMETRICID(String mETRICID) {
		METRICID = mETRICID;
	}
	public String getVISITORID() {
		return VISITORID;
	}
	public void setVISITORID(String vISITORID) {
		VISITORID = vISITORID;
	}
	public String getMETRICNAME() {
		return METRICNAME;
	}
	public void setMETRICNAME(String mETRICNAME) {
		METRICNAME = mETRICNAME;
	}
	public String getMETRICTYPE() {
		return METRICTYPE;
	}
	public void setMETRICTYPE(String mETRICTYPE) {
		METRICTYPE = mETRICTYPE;
	}
	public String getTVALUES() {
		return TVALUES;
	}
	public void setTVALUES(String tVALUES) {
		TVALUES = tVALUES;
	}

}
