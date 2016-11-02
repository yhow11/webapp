package service.urlmanagement.urlimport.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;

@PhoenixTable(table="urlImportTable")
public class URLImportModel{
	
	@PhoenixID
	@PhoenixColumn(type="VARCHAR(250)")
	protected String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
