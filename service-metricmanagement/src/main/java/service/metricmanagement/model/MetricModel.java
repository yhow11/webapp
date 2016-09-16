package service.metricmanagement.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="metricTable")
public class MetricModel {

	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	private Long ID;
	@PhoenixColumn(type="VARCHAR(100)")
	private String NAME;
	@PhoenixColumn(type="VARCHAR(100)")
	private String TYPE;
	@PhoenixColumn(type="VARCHAR(100)")
	private String TKEY;
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
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getTKEY() {
		return TKEY;
	}
	public void setTKEY(String tKEY) {
		TKEY = tKEY;
	}
	
}
