package service.urlmanagement.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.annotation.query.PhoenixDistinctColumn;

@PhoenixTable(table="urlTable")
public class URLDistinctKeyModel {
	
	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	private Long id;
	@PhoenixDistinctColumn
	@PhoenixColumn(type="VARCHAR(100)")
	private String url;
	@PhoenixColumn(type="BIGINT")
	private Long keyID;
	@PhoenixColumn(type="BIGINT")
	private Long valueID;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getKeyID() {
		return keyID;
	}
	public void setKeyID(Long keyID) {
		this.keyID = keyID;
	}
	public Long getValueID() {
		return valueID;
	}
	public void setValueID(Long valueID) {
		this.valueID = valueID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
