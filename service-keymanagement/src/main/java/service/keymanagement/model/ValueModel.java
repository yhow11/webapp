package service.keymanagement.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.object.PaginationQuery;

@PhoenixTable(table="valueTable")
public class ValueModel extends PaginationQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411056548892215175L;
	
	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	protected Long id;
	@PhoenixColumn(type="VARCHAR(100)")
	protected String tKey;
	@PhoenixColumn(type="VARCHAR(100)")
	protected String tValue;
	
	public String gettKey() {
		return tKey;
	}
	public void settKey(String tKey) {
		this.tKey = tKey;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String gettValue() {
		return tValue;
	}
	public void settValue(String tValue) {
		this.tValue = tValue;
	}
	
	
}
