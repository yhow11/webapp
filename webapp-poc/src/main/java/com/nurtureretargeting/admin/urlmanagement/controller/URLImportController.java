package com.nurtureretargeting.admin.urlmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.admin.urlmanagement.manager.URLImportManager;
import com.nurtureretargeting.admin.urlmanagement.object.URLImportForm;

import common.form.ResponsePaginationForm;

@Controller
public class URLImportController {
	
	@Autowired
	private URLImportManager URLImportManager;

	@RequestMapping(value = "urlimport/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponsePaginationForm<URLImportForm> getAll(@RequestParam("sitemapURL") String sitemapURL) throws Exception {
		 
		ResponsePaginationForm<URLImportForm> response =  URLImportManager.getAll(sitemapURL);
		response.setStatus(true);
		return response;
	}
	

	@RequestMapping(value = "urlimport/save", method = RequestMethod.POST)
	public @ResponseBody  ResponsePaginationForm<URLImportForm> save(@RequestBody List<URLImportForm> urlForms) throws Exception {

		ResponsePaginationForm<URLImportForm> response = URLImportManager.save(urlForms);
		return response;
	}
	
}
