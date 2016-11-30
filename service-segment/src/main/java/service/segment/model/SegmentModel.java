package service.segment.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="segmentTable")
public class SegmentModel {

	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	protected Long ID;
	
	@PhoenixColumn(type="VARCHAR(100)")
	protected String NAME;
	
	@PhoenixColumn(type="BIGINT")
	protected Long METRICID;
	
	@PhoenixColumn(type="VARCHAR(100)")
	protected String FILTER;
	
	@PhoenixColumn(type="VARCHAR(100)")
	protected String TVALUE;

	@PhoenixColumn(type="VARCHAR(200)")
	protected String CODE;
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public Long getMETRICID() {
		return METRICID;
	}

	public void setMETRICID(Long mETRICID) {
		METRICID = mETRICID;
	}

	public String getFILTER() {
		return FILTER;
	}

	public void setFILTER(String fILTER) {
		FILTER = fILTER;
	}

	public String getTVALUE() {
		return TVALUE;
	}

	public void setTVALUE(String vALUE) {
		TVALUE = vALUE;
	}
	
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	
}
