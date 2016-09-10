package com.nurtureretargeting.admin.urlmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.admin.urlmanagement.object.URLForm;
import com.nurtureretargeting.admin.urlmanagement.service.URLManagementService;
import com.nurtureretargeting.object.ResponsePaginationForm;

@Controller
public class URLTaggingController {

	@Autowired
	private URLManagementService URLTaggingService;
	
	@RequestMapping(value = "urltagging/getAllURLs", method = RequestMethod.GET)
	public @ResponseBody  ResponsePaginationForm<URLForm> getAll(@RequestParam("url") String url, @RequestParam("page") String page, @RequestParam("limit") String limit) throws Exception {
		 
		ResponsePaginationForm<URLForm> response =  URLTaggingService.getAllTaggedURLs(url, page, limit);
		response.setStatus(true);
		return response;
	}
	
	@RequestMapping(value = "urltagging/save", method = RequestMethod.POST)
	public @ResponseBody  ResponsePaginationForm<URLForm> save(@RequestBody List<URLForm> urlForms) throws Exception {
		
		ResponsePaginationForm<URLForm> response =  URLTaggingService.saveTaggedURLs(urlForms);
		response.setStatus(true);
		return response;
	}
}
