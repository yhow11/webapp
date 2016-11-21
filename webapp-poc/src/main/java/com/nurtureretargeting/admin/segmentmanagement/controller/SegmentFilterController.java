package com.nurtureretargeting.admin.segmentmanagement.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.ResponseForm;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.segment.filter.form.FilterForm;

@Controller
public class SegmentFilterController {

	@Resource(name="${SegmentFilterController.storage}")
	private Storage<FilterForm> storage;
	
	@RequestMapping(value = "segment/filter/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<FilterForm> getAll(@RequestParam("metrictype") String metrictype) throws Exception {
		Param<FilterForm> param = new DefaultParam<>(FilterForm.class);
		param.getModel().setMetricType(metrictype);
		return new ResponseForm<>(storage.get(param));
	}
}
