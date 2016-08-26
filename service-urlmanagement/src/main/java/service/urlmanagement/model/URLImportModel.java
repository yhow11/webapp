package service.urlmanagement.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.object.QueryHelperParam;

@PhoenixTable(table="urlImportTable")
public class URLImportModel extends QueryHelperParam {
	
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
