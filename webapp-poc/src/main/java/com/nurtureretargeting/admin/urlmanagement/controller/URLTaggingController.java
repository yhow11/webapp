package com.nurtureretargeting.admin.urlmanagement.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.Page;
import common.form.ResponseForm;
import common.orm.query.Book;
import common.orm.query.Storage;
import common.orm.query.param.DefaultPageParam;
import common.orm.query.param.Param;
import service.urlmanagement.url.form.URLForm;

@Controller
public class URLTaggingController {

	@Resource(name="${URLTaggingController.book}")
	private Book<URLForm> book;
	@Resource(name="${URLTaggingController.storage}")
	private Storage<URLForm> storage;
	
	@RequestMapping(value = "urltagging/getAll", method = RequestMethod.GET)
	public @ResponseBody  Page<URLForm> getAll(@RequestParam("url") String url, @RequestParam("page") String page, @RequestParam("limit") String limit) throws Exception {
		Param<URLForm> param = new DefaultPageParam<URLForm>(URLForm.class, page, limit);
		param.getModel().setUrl(url);
		return  book.getPage(param);
	}
	
	@RequestMapping(value = "urltagging/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<URLForm> save(@RequestBody List<URLForm> urlForms) throws Exception {
		return new ResponseForm<>(storage.save(urlForms));
	}
}
