package com.fingerprint.event.controller;

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

import com.fingerprint.event.object.FingerPrintFormParam;
import com.fingerprint.event.object.FingerPrintSectionForm;
import com.fingerprint.event.service.EventService;
import com.fingerprint.object.ResponseForm;
import com.fingerprint.util.object.EventRequestObject;
import com.fingerprint.util.service.impl.KafkaService;

import usertracker.base.UserParam;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;


@Controller
public class EventsController {

	@Autowired
	private KafkaService kafkaService;

	@Autowired
	private EventService eventService;
	
	
	@Autowired
	private SimpMessagingTemplate template;
	
	
	
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
	@RequestMapping(value = "notifyEvents", method = RequestMethod.POST)
	public String notifyEvents(@RequestBody UserParam<?> data) throws Exception {
		template.convertAndSend("/event/notifyReceivers",data);
		return "success";
	}
	
    @MessageMapping("/send")
    @SendTo("/event/updates")
    public ResponseForm<?> send(EventRequestObject request) {
    	
    	ResponseForm<?> response = new ResponseForm<>();
    	response.setStatus(true);
    	try {
    		kafkaService.send(request.getLog());
    	} catch( Exception e) {
    		response.setStatus(false);
    	}
    	
        return response;
    }
    
    @MessageMapping("/notify")
    @SendTo("/event/notifyReceivers")
    public ResponseForm notifyReceivers() {
    	
    	ResponseForm response = new ResponseForm<>();
    	response.setStatus(true);
        return response;
    }
}
