package com.nurtureretargeting.admin.urlmanagement.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.ResponseForm;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.urlmanagement.urlimport.form.URLImportForm;

@Controller
public class URLImportController {
	
	@Resource(name="${URLImportController.siteMapStorage}")
	private Storage<URLImportForm> siteMapStorage;
	
	@Resource(name="${URLImportController.importStorage}")
	private Storage<URLImportForm> importStorage;
	
	@RequestMapping(value = "urlimport/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<URLImportForm> getAll(@RequestParam("sitemapURL") String sitemapURL) throws Exception {
		Param<URLImportForm> param = new DefaultParam<URLImportForm>(URLImportForm.class);
		param.getModel().setUrl(sitemapURL);
		return new ResponseForm<>(siteMapStorage.get(param));
	}
	

	@RequestMapping(value = "urlimport/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<URLImportForm> save(@RequestBody List<URLImportForm> urlForms) throws Exception {
		return new ResponseForm<>(importStorage.save(urlForms));
	}
	
}
