package com.nurtureretargeting.tracker.webevent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.tracker.visitor.object.FingerPrintDetail;
import com.nurtureretargeting.tracker.webevent.manager.WebEventManager;

import common.form.ResponseForm;
import usertracker.browser.model.WebEventModel;

@Controller
public class WebEventController {

	@Autowired
	private WebEventManager webEventManager;
	
	@RequestMapping(value = "webevent/getAll", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<WebEventModel> getAll(@RequestBody FingerPrintDetail detail, @RequestParam("start") String start, @RequestParam("end") String end) throws Exception {
		
		ResponseForm<WebEventModel> response =  new ResponseForm<WebEventModel>();
		
		List<WebEventModel> events =  webEventManager.getAll(detail.getSessionID(), detail.getBrowserFP(), start, end);
		
		if(events.size() > 0) {
			response.setStatus(true);
			response.setData(events);
		} else {
			response.setStatus(false);
	    	response.setMessage(ResponseForm.NO_DATA);
		}
		
		return response;
	}
	
}
