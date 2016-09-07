package service.urlmanagement.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.annotation.query.PhoenixDistinctColumn;
import helper.phoenix.object.QueryHelperParam;

@PhoenixTable(table="urlImportTable")
public class URLImportGroupByURLModel {
	
	@PhoenixID
	@PhoenixDistinctColumn
	@PhoenixColumn(type="VARCHAR(100)")
	protected String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
