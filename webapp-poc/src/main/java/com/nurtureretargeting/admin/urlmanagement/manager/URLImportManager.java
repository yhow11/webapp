package com.nurtureretargeting.admin.urlmanagement.manager;

import java.util.List;

import com.nurtureretargeting.admin.urlmanagement.object.URLImportForm;

import common.form.ResponsePaginationForm;
import service.urlmanagement.URLSiteMapService;
import service.urlmanagement.model.URLImportModel;
import service.urlmanagement.URLImportService;
import service.urlmanagement.model.URLSiteMapModel;

public class URLImportManager {

	private URLSiteMapService URLSiteMapService;
	private URLImportService URLImportService;
	
	public URLImportManager(URLImportService URLImportService, URLSiteMapService URLSiteMapService) {
		// TODO Auto-generated constructor stub
		this.URLSiteMapService = URLSiteMapService;
		this.URLImportService = URLImportService;
	}
	public ResponsePaginationForm<URLImportForm> getAll(String url) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLImportForm> response = new ResponsePaginationForm<>();
		List<URLSiteMapModel> sitemapURLs = URLSiteMapService.getAll(url);
		for (URLSiteMapModel sitemapURL : sitemapURLs) {
			URLImportForm urlImportForm = new URLImportForm();
			urlImportForm.setUrl(sitemapURL.getUrl());
			response.getData().add(urlImportForm);
		}
		response.setStatus(true);

		return response;
	}
	
	public ResponsePaginationForm<URLImportForm> save(List<URLImportForm> urlForms) throws Exception {
		for (URLImportForm urlForm : urlForms) {
			URLImportModel urlImportModel = new URLImportModel();
			urlImportModel.setUrl(urlForm.getUrl());
			URLImportService.save(urlImportModel);
		}

		ResponsePaginationForm<URLImportForm> response = new ResponsePaginationForm<URLImportForm>();
		response.setStatus(true);
		response.getData().addAll(urlForms);
		return response;
	}
}
