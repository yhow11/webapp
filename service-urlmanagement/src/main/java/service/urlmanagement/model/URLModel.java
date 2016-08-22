package service.urlmanagement.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="urlTable")
public class URLModel {
	
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	protected String url;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	protected String tKey;
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	protected String tValues;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
