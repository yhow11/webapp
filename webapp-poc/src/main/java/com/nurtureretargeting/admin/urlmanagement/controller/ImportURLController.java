package com.nurtureretargeting.admin.urlmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.admin.urlmanagement.object.URLImportForm;
import com.nurtureretargeting.admin.urlmanagement.service.URLManagementService;
import com.nurtureretargeting.object.ResponsePaginationForm;

@Controller
public class ImportURLController {
	
	@Autowired
	private URLManagementService URLManagementService;

	@RequestMapping(value = "importurl/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponsePaginationForm<URLImportForm> getAll(@RequestParam("sitemapURL") String sitemapURL) throws Exception {
		 
		ResponsePaginationForm<URLImportForm> response =  URLManagementService.getAllSiteMapURLs(sitemapURL);
		response.setStatus(true);
		return response;
	}
	

	@RequestMapping(value = "importurl/save", method = RequestMethod.POST)
	public @ResponseBody  ResponsePaginationForm<URLImportForm> save(@RequestBody List<URLImportForm> urlForms) throws Exception {

		ResponsePaginationForm<URLImportForm> response = URLManagementService.saveImportURLs(urlForms);
		return response;
	}
	
}
