package com.fingerprint.monitoring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fingerprint.object.ResponseForm;

import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.service.VisitorLogService;

@Controller
public class MonitoringController {

	@Autowired
	private VisitorLogService visitorLogService;
	
	@RequestMapping(value = "monitoring/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<VisitorLogModel> getAll(@RequestParam("offset") Long offset, @RequestParam("limit") Long limit) throws Exception {
//		
//		visitorLogService.getAll(start, end)
		ResponseForm<VisitorLogModel> response =  new ResponseForm<VisitorLogModel>();
//		List<VisitorLogModel> events =  eventService.getVisitorLogs(start, end);

		response.setStatus(true);
//		response.setData(events);
		
		return response;
	}
}
