package com.fingerprint.urltagging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fingerprint.object.ResponsePaginationForm;
import com.fingerprint.urltagging.object.URLForm;
import com.fingerprint.urltagging.service.URLTaggingService;

@Controller
public class URLTaggingController {

	@Autowired
	private URLTaggingService URLTaggingService;
	
	@RequestMapping(value = "urltagging/getAllURLs", method = RequestMethod.GET)
	public @ResponseBody  ResponsePaginationForm<URLForm> getAll(@RequestParam("url") String url, @RequestParam("page") String page, @RequestParam("limit") String limit) throws Exception {
		 
		ResponsePaginationForm<URLForm> response =  URLTaggingService.getAllURLs(url, page, limit);
		response.setStatus(true);
		return response;
	}
	
	@RequestMapping(value = "urltagging/save", method = RequestMethod.POST)
	public @ResponseBody  ResponsePaginationForm<URLForm> save(@RequestBody List<URLForm> urlForms) throws Exception {
		
		ResponsePaginationForm<URLForm> response =  URLTaggingService.save(urlForms);
		response.setStatus(true);
		return response;
	}
}
