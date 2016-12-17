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
import service.metricmanagement.metricsummary.form.MetricSummaryForm;

@Controller
public class MetricSummaryController {

	@Resource(name="${MetricSummaryController.storage}")
	private Storage<MetricSummaryForm> storage;
	@Resource(name="${MetricSummaryController.book}")
	private Book<MetricSummaryForm> book;
	
	@RequestMapping(value = "metric/summary/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<MetricSummaryForm> getAll(@RequestParam("type") String type, @RequestParam("offset") String offset, @RequestParam("limit") String limit) throws Exception {
		Param<MetricSummaryForm> param = new DefaultParam<>(MetricSummaryForm.class, offset, limit);
		param.getModel().setMetricType(type);
		return new ResponseForm<>(storage.get(param));
	}
	
	@RequestMapping(value = "metric/summary/getPage", method = RequestMethod.POST)
	public @ResponseBody  Page<MetricSummaryForm> getPage(@RequestBody PageRequest<MetricSummaryForm> request) throws Exception {
		Param<MetricSummaryForm> param = new DefaultParam<MetricSummaryForm>(MetricSummaryForm.class, request.getPage(), request.getLimit());
		param.setModel(request.getModel());
		return book.getPage(param);
	}
}
