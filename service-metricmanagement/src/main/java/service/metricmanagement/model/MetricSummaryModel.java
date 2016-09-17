package service.metricmanagement.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="metricSummaryTable")
public class MetricSummaryModel {

	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	private String VISITORID;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	private String METRICNAME;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	private String METRICTYPE;
	@PhoenixColumn(type="VARCHAR(100)")
	private String TVALUES;
	
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
