package service.keymanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.object.PaginationQuery;

@PhoenixTable(table="keyTable")
public class KeyModel extends PaginationQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411056548892215175L;
	
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	protected String tKey;
	
	protected List<ValueModel> values = new ArrayList<>();
	
	public String gettKey() {
		return tKey;
	}
	public void settKey(String tKey) {
		this.tKey = tKey;
	}
	public List<ValueModel> getValues() {
		return values;
	}
	public void setValues(List<ValueModel> values) {
		this.values = values;
	}
	
	
}