package com.fingerprint.event.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fingerprint.object.FingerPrintFormParam;
import com.fingerprint.object.FingerPrintResponseForm;
import com.fingerprint.object.ResponseForm;
import com.fingerprint.util.Utilities;
import com.fingerprint.util.object.EventRequestObject;
import com.fingerprint.util.service.impl.KafkaService;

import usertracker.base.UserParam;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.VisitorLogService;


@Controller
public class EventsController {

	@Autowired
	private KafkaService kafkaService;

	@Autowired
	private VisitorLogService visitorLogService;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	
	@RequestMapping(value = "event/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<VisitorLogModel> getAll(@RequestParam("start") String start) throws Exception {
		
		ResponseForm<VisitorLogModel> response =  new ResponseForm<VisitorLogModel>();
		List<VisitorLogModel> events =  visitorLogService.getAll(VisitorLogModel.class, "timestamp"
				,String.valueOf(Utilities.getDate(new Date(Long.valueOf(start)), -3).getTime()) , String.valueOf(new Date(Long.valueOf(start)).getTime()), "desc");

		response.setStatus(true);
		response.setData(events);
		
		
		
		return response;
	}
	
	@RequestMapping(value = "event/getWebEvents", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<WebEventModel> getWebEvents(@RequestBody FingerPrintFormParam fingerPrintFormParam, @RequestParam("start") String start) throws Exception {
		
		ResponseForm<WebEventModel> response =  new ResponseForm<WebEventModel>();
		SessionModel session =  visitorLogService.getOne(SessionModel.class, fingerPrintFormParam.getSessionID());

		if(session != null) {
			List<WebEventModel> events =  visitorLogService.findWebEvents(session.getAnonymousVisitorID(),String.valueOf(Utilities.getDate(new Date(Long.valueOf(start)), -3).getTime()) , String.valueOf(new Date(Long.valueOf(start)).getTime()), "desc");
			response.setStatus(true);
			response.setData(events);
		} else {
			BrowserFPModel browserFP = visitorLogService.getOne(BrowserFPModel.class, fingerPrintFormParam.getBrowserFP());
		    if(browserFP != null) {
		    	List<WebEventModel> events =  visitorLogService.findWebEvents(browserFP.getAnonymousVisitorID(),String.valueOf(Utilities.getDate(new Date(Long.valueOf(start)), -3).getTime()) , String.valueOf(new Date(Long.valueOf(start)).getTime()), "desc");
				response.setStatus(true);
				response.setData(events);
		    }
		}
		
		return response;
	}
	

	@RequestMapping(value = "event/getSessions", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<SessionModel> getSessions(@RequestBody FingerPrintFormParam fingerPrintFormParam, @RequestParam("start") String start) throws Exception {
		
		ResponseForm<SessionModel> response =  new ResponseForm<SessionModel>();
		SessionModel session =  visitorLogService.getOne(SessionModel.class, fingerPrintFormParam.getSessionID());

		if(session != null) {
			List<SessionModel> sessions = visitorLogService.find(SessionModel.class, "anonymousVisitorID", session.getAnonymousVisitorID());
			response.setStatus(true);
			response.setData(sessions);
		} else {
			BrowserFPModel browserFP = visitorLogService.getOne(BrowserFPModel.class, fingerPrintFormParam.getBrowserFP());
		    if(browserFP != null) {
		    	List<SessionModel> sessions = visitorLogService.find(SessionModel.class, "anonymousVisitorID", browserFP.getAnonymousVisitorID());
				response.setStatus(true);
				response.setData(sessions);
		    }
		}
		
		return response;
	}
	
	@RequestMapping(value = "event/getAnonymousUser", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<FingerPrintResponseForm> getAll(@RequestBody FingerPrintFormParam fingerPrintFormParam) throws Exception {
		
		ResponseForm<FingerPrintResponseForm> response =  new ResponseForm<FingerPrintResponseForm>();
		FingerPrintResponseForm form = new FingerPrintResponseForm();
		
		SessionModel session =  visitorLogService.getOne(SessionModel.class, fingerPrintFormParam.getSessionID());

		if(session != null) {
			form.setAnonymousUserID(session.getAnonymousVisitorID());
			form.setBrowserFPs(visitorLogService.find(BrowserFPModel.class, "anonymousVisitorID", session.getAnonymousVisitorID()));
			form.setDeviceFPs(visitorLogService.find(DeviceFPModel.class, "anonymousVisitorID", session.getAnonymousVisitorID()));
			
		} else {
			BrowserFPModel browserFP = visitorLogService.getOne(BrowserFPModel.class, fingerPrintFormParam.getBrowserFP());
		    if(browserFP != null) {
		    	form.setAnonymousUserID(browserFP.getAnonymousVisitorID());
		    	form.setBrowserFPs(visitorLogService.find(BrowserFPModel.class, "anonymousVisitorID", browserFP.getAnonymousVisitorID()));
				form.setDeviceFPs(visitorLogService.find(DeviceFPModel.class, "anonymousVisitorID", browserFP.getAnonymousVisitorID()));
				
		    } else {
		    	response.setStatus(false);
		    	response.setMessage(ResponseForm.NO_DATA);
		    }
		
		}
		response.setStatus(true);
		response.getData().add(form);
		
		
		
		return response;
	}
	@RequestMapping(value = "event/test", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<VisitorLogModel> test() throws Exception {
		List<VisitorLogModel> events = visitorLogService.find(VisitorLogModel.class, "visited","type");
		ResponseForm<VisitorLogModel> response = new ResponseForm<>();
		
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        rt.getMessageConverters().add(new StringHttpMessageConverter());

        String uri = new String("http://localhost:8080/webapp-poc/notifyEvents");


        String result = rt.postForObject(uri, events, String.class);
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
