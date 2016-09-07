package service.urlmanagement;

import java.util.List;

import service.urlmanagement.model.URLImportModel;

public interface URLSiteMapService{
	public List<URLImportModel> getAll(String url) throws Exception;
}
