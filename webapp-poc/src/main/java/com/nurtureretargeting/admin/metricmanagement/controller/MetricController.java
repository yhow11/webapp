package com.nurtureretargeting.admin.metricmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.admin.metricmanagement.manager.MetricManager;
import com.nurtureretargeting.admin.metricmanagement.object.MetricForm;

import common.form.ResponseForm;

@Controller
public class MetricController {

	@Autowired
	private MetricManager metricManager;
	
	@RequestMapping(value = "metric/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<MetricForm> getAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) throws Exception {
		ResponseForm<MetricForm> response = new ResponseForm<MetricForm>();
		response.setStatus(true);
		response.getData().addAll(metricManager.getAll(offset, limit));
		return response;
	}
	
	@RequestMapping(value = "metric/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<MetricForm> save(@RequestBody MetricForm form) throws Exception {
		ResponseForm<MetricForm> response = new ResponseForm<MetricForm>();
		response.getData().add(metricManager.save(form));
		return response;
	}
	
	@RequestMapping(value = "metric/remove", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<MetricForm> remove(@RequestBody MetricForm form) throws Exception {
		ResponseForm<MetricForm> response = new ResponseForm<MetricForm>();
		metricManager.remove(form);
		response.setStatus(true);
		return response;
	}
}
