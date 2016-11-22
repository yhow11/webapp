package service.segment.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="segmentQualifiedVisitorModel")
public class SegmentedVisitorModel {

	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	private String VISITORID;
	
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(255)")
	protected String SEGMENTID;

	public String getVISITORID() {
		return VISITORID;
	}

	public void setVISITORID(String vISITORID) {
		VISITORID = vISITORID;
	}

	public String getSEGMENTID() {
		return SEGMENTID;
	}

	public void setSEGMENTID(String sEGMENTID) {
		SEGMENTID = sEGMENTID;
	}

	
	
}
