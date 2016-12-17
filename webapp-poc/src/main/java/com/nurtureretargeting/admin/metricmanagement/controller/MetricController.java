package com.nurtureretargeting.admin.metricmanagement.controller;

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
import service.metricmanagement.metric.form.MetricForm;

@Controller
public class MetricController {

	@Resource(name="${MetricController.storage}")
	private Storage<MetricForm> storage;
	@Resource(name="${MetricController.book}")
	private Book<MetricForm> book;
	
	
	@RequestMapping(value = "metric/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<MetricForm> getAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) throws Exception {
		return new ResponseForm<MetricForm>(storage.get(new DefaultParam<>(MetricForm.class, offset, limit)));
	}
	
	@RequestMapping(value = "metric/search", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<MetricForm> search(@RequestParam("query") String query) throws Exception {
		Param<MetricForm> param = new DefaultParam<>(MetricForm.class);
		param.getModel().setName("%"+query+"%");
		return new ResponseForm<MetricForm>(storage.get(param));
	}
	
	@RequestMapping(value = "metric/get", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<MetricForm> get(@RequestParam("id") String id) throws Exception {
		Param<MetricForm> param = new DefaultParam<>(MetricForm.class, null, "1");
		param.getModel().setId(id);
		return new ResponseForm<MetricForm>(storage.get(param).stream().findFirst().get());
	}
	
	@RequestMapping(value = "metric/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<MetricForm> save(@RequestBody MetricForm form) throws Exception {
		return  new ResponseForm<MetricForm>(storage.save(form));
	}
	
	@RequestMapping(value = "metric/remove", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<MetricForm> remove(@RequestBody MetricForm form) throws Exception {
		Param<MetricForm> param = new DefaultParam<>(MetricForm.class);
		param.getModel().setId(form.getId());
		storage.remove(storage.get(param).stream().findFirst().get());
		return new ResponseForm<MetricForm>();
	}
	
	@RequestMapping(value = "metric/getPage", method = RequestMethod.POST)
	public @ResponseBody  Page<MetricForm> getPage(@RequestBody PageRequest<MetricForm> request) throws Exception {
		Param<MetricForm> param = new DefaultParam<MetricForm>(MetricForm.class, request.getPage(), request.getLimit());
		param.setModel(request.getModel());
		return book.getPage(param);
	}
}
