package com.fingerprint.event.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fingerprint.object.ResponseForm;
import com.fingerprint.util.model.EventCountModel;
import com.fingerprint.util.object.EventRequestObject;
import com.fingerprint.util.service.impl.KafkaService;

import usertracker.browser.model.VisitorLogModel;
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
	public @ResponseBody  ResponseForm<VisitorLogModel> getAll() throws Exception {
		
		ResponseForm<VisitorLogModel> response = new ResponseForm<>();
		try{
			List<VisitorLogModel> events = visitorLogService.getAll();
			response.setStatus(true);
			response.setData(events);
		}catch( Exception e) {
//    		response.setStatus(false);
    	}
		

		
		return response;
	}
	@RequestMapping(value = "event/test", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<VisitorLogModel> test() throws Exception {
		List<VisitorLogModel> events = visitorLogService.find("visited","type");
		ResponseForm<VisitorLogModel> response = new ResponseForm<>();
		
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        rt.getMessageConverters().add(new StringHttpMessageConverter());

        String uri = new String("http://localhost:8080/webapp-poc/notifyEvents");


        String result = rt.postForObject(uri, events, String.class);
		return response;
	}
	@RequestMapping(value = "notifyEvents", method = RequestMethod.POST)
	public String notifyEvents(@RequestBody List<VisitorLogModel> events) throws Exception {
		template.convertAndSend("/event/notifyReceivers",events);
		return "success";
	}
	
    @MessageMapping("/send")
    @SendTo("/event/updates")
    public ResponseForm<EventCountModel> send(EventRequestObject request) {
    	
    	ResponseForm<EventCountModel> response = new ResponseForm<>();
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
