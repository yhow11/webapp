package com.nurtureretargeting.admin.metricmanagement.controller;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value = "metric/summary/count", method = RequestMethod.GET)
	public @ResponseBody  Map<String, Object> count() throws Exception {
		return Collections.singletonMap("count", book.count(new MetricSummaryForm()));
	}
}
