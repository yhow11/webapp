package com.nurtureretargeting.tracker.log.controller;

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

import common.form.ResponseForm;
import usertracker.base.UserParam;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.service.VisitorLogService;
import usertracker.browser.service.VisitorRawLogService;


@Controller
public class LogsController {
	
	@Autowired
	private VisitorLogService visitorLogService;
	
	@Autowired
	private VisitorRawLogService visitorRawLogService;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@RequestMapping(value = "logs/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<VisitorLogModel> getAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) throws Exception {
		
		ResponseForm<VisitorLogModel> response =  new ResponseForm<VisitorLogModel>();
		List<VisitorLogModel> events =  visitorLogService.getAll(Long.valueOf(offset), Long.valueOf(limit));

		response.setStatus(true);
		response.setData(events);
		
		return response;
	}
	

	@RequestMapping(value = "/logs/broadcast", method = RequestMethod.POST)
	public String broadcast(@RequestBody UserParam<?> data) throws Exception {
		template.convertAndSend("/logs/broadcast",data);
		return "success";
	}
	
    @MessageMapping("/send")
    @SendTo("/logs/updates")
    public ResponseForm<?> send(String log) {
    	
    	ResponseForm<?> response = new ResponseForm<>();
    	response.setStatus(true);
    	try {
    		visitorRawLogService.save(log);
    	} catch( Exception e) {
    		response.setStatus(false);
    	}
    	
        return response;
    }
}
