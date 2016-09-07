package com.fingerprint.log.controller;

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
public class LogsController {
	
	@Autowired
	private VisitorLogService visitorLogService;
	
	
	@RequestMapping(value = "logs/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<VisitorLogModel> getAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) throws Exception {
		
		ResponseForm<VisitorLogModel> response =  new ResponseForm<VisitorLogModel>();
		List<VisitorLogModel> events =  visitorLogService.getAll(Long.valueOf(offset), Long.valueOf(limit));

		response.setStatus(true);
		response.setData(events);
		
		return response;
	}
}
