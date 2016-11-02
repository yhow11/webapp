package service.keymanagement.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="keyTable")
public class KeyModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411056548892215175L;
	
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	protected String tKey;
	
	@PhoenixColumn(type="VARCHAR(100)")
	protected String tValues;
	
	public String gettKey() {
		return tKey;
	}
	public void settKey(String tKey) {
		this.tKey = tKey;
	}
	public String gettValues() {
		return tValues;
	}
	public void settValues(String tValues) {
		this.tValues = tValues;
	}
	
	
	
}
