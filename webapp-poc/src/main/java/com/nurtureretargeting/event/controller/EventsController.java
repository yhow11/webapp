package com.nurtureretargeting.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.event.object.FingerPrintFormParam;
import com.nurtureretargeting.event.object.FingerPrintSectionForm;
import com.nurtureretargeting.event.service.EventService;
import com.nurtureretargeting.object.ResponseForm;
import com.nurtureretargeting.util.service.impl.KafkaService;

import usertracker.base.UserParam;
import usertracker.browser.model.WebEventModel;


@Controller
public class EventsController {


	@Autowired
	private EventService eventService;
	
	
	
	@RequestMapping(value = "event/getWebEvents", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<WebEventModel> getWebEvents(@RequestBody FingerPrintFormParam fingerPrintFormParam, @RequestParam("start") String start, @RequestParam("end") String end) throws Exception {
		
		ResponseForm<WebEventModel> response =  new ResponseForm<WebEventModel>();
		
		List<WebEventModel> events =  eventService.getWebEvents(fingerPrintFormParam.getSessionID(), fingerPrintFormParam.getBrowserFP(), start, end);
		
		if(events.size() > 0) {
			response.setStatus(true);
			response.setData(events);
		} else {
			response.setStatus(false);
	    	response.setMessage(ResponseForm.NO_DATA);
		}
		
		return response;
	}
	

	
	@RequestMapping(value = "event/getAnonymousUser", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<FingerPrintSectionForm> getAll(@RequestBody FingerPrintFormParam fingerPrintFormParam) throws Exception {
		
		ResponseForm<FingerPrintSectionForm> response =  new ResponseForm<FingerPrintSectionForm>();
		response.setStatus(true);
		response.getData().add(eventService.createFingerPrintSectionForm(fingerPrintFormParam.getSessionID(), fingerPrintFormParam.getBrowserFP()));
	
		return response;
	}
}
