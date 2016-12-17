package com.nurtureretargeting.admin.keymanagement.controller;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.PageRequest;
import common.form.Page;
import common.form.ResponseForm;
import common.orm.query.Book;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.keymanagement.form.KeyForm;


@Controller
public class KeyManagementController {

	@Resource(name="${KeyManagementController.storage}")
	private Storage<KeyForm> storage;
	@Resource(name="${KeyManagementController.book}")
	private Book<KeyForm> book;
	
	@RequestMapping(value = "key/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<KeyForm> save(@RequestBody KeyForm keyForm) throws Exception {
		return new ResponseForm<KeyForm>(storage.save(keyForm));
	}
	
	@RequestMapping(value = "key/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<KeyForm> getAll(@RequestParam(name="value") String value, @RequestParam(name="start") String start, @RequestParam(name="end") String end) throws Exception {
		Param<KeyForm> param = new DefaultParam<KeyForm>(KeyForm.class, start, end);
		param.getModel().setKey(value);
		return new ResponseForm<KeyForm>(storage.get(param));
	}
	
	@RequestMapping(value = "key/getPage", method = RequestMethod.POST)
	public @ResponseBody  Page<KeyForm> getAll(@RequestBody PageRequest<KeyForm> request) throws Exception {
		Param<KeyForm> param = new DefaultParam<KeyForm>(KeyForm.class, request.getPage(), request.getLimit());
		return book.getPage(param);
	}
	
	@RequestMapping(value = "key/checkExists", method = RequestMethod.GET)
	public @ResponseBody Map<String, Boolean> checkExists(@RequestParam(name="key") String key) throws Exception {
		Param<KeyForm> param = new DefaultParam<KeyForm>(KeyForm.class);
		param.getModel().setKey(key);
		return Collections.singletonMap("data", storage.get(param).stream().findFirst().isPresent());
	}
	
	@RequestMapping(value = "key/delete", method = RequestMethod.DELETE)
	public @ResponseBody  ResponseForm<KeyForm> delete(@RequestParam(name="key") String key) throws Exception {
		Param<KeyForm> param = new DefaultParam<KeyForm>(KeyForm.class);
		param.getModel().setKey(key);
		storage.remove(storage.get(param).stream().findFirst().get());
		return new ResponseForm<>();
	}
}
