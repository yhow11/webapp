package com.nurtureretargeting.admin.metricmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import common.form.ResponseForm;
import service.metricmanagement.metrictype.form.MetricTypeForm;

@Controller
public class MetricTypeController {

	@RequestMapping(value = "metrictype/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<MetricTypeForm> getAll() throws Exception {
		ResponseForm<MetricTypeForm> response = new ResponseForm<MetricTypeForm>();
		MetricTypeForm form = new MetricTypeForm();
		form.setName("Page Count");
		form.setType("PAGECOUNT");
		response.getData().add(form);
		
		form = new MetricTypeForm();
		form.setName("Time On Page");
		form.setType("TIMEONPAGE");
		response.getData().add(form);
		
		form = new MetricTypeForm();
		form.setName("Key Sum");
		form.setType("KEYSUM");
		response.getData().add(form);
		return response;
	}
}
