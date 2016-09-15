package service.urlmanagement;

import java.util.List;

import service.urlmanagement.model.URLSiteMapModel;

public interface URLSiteMapService{
	public List<URLSiteMapModel> getAll(String url) throws Exception;
}
