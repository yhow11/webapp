package com.nurtureretargeting.admin.metricmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.admin.metricmanagement.manager.MetricSummaryManager;
import com.nurtureretargeting.admin.metricmanagement.object.MetricSummaryForm;

import common.form.ResponseForm;

@Controller
public class MetricSummaryController {

	@Autowired
	private MetricSummaryManager metricSummaryManager;
	
	@RequestMapping(value = "metric/summary/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<MetricSummaryForm> getAll(@RequestParam("type") String type, @RequestParam("offset") String offset, @RequestParam("limit") String limit) throws Exception {
		ResponseForm<MetricSummaryForm> response = new ResponseForm<MetricSummaryForm>();
		response.setStatus(true);
		response.getData().addAll(metricSummaryManager.getAll(type, offset, limit));
		return response;
	}
	
}
