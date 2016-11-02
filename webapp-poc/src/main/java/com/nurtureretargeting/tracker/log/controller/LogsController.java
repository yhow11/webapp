package com.nurtureretargeting.tracker.log.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.ResponseForm;
import common.orm.query.Queue;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import usertracker.browser.visitorlog.form.VisitorLogForm;


@Controller
public class LogsController {
	
	@Resource(name="${LogsController.storage}")
	private Storage<VisitorLogForm> storage;
	
	@Resource(name="${LogsController.queue}")
	private Queue<String> queue;
	
	@RequestMapping(value = "logs/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<VisitorLogForm> getAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) throws Exception {
		
		ResponseForm<VisitorLogForm> response =  new ResponseForm<VisitorLogForm>();
		List<VisitorLogForm> events =  storage.get(new DefaultParam<>(VisitorLogForm.class, offset, limit));

		response.setStatus(true);
		response.setData(events);
		
		return response;
	}
	
	@RequestMapping(value = "logs/send", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<?> send(@RequestParam("log") String log) throws Exception {
		
		ResponseForm<?> response = new ResponseForm<>();
    	response.setStatus(true);
    	try {
    		System.out.println(log);
    		queue.send(log);
    	} catch( Exception e) {
    		response.setStatus(false);
    	}
    	
        return response;
	}

//	@RequestMapping(value = "/logs/broadcast", method = RequestMethod.POST)
//	public String broadcast(@RequestBody UserParam<?> data) throws Exception {
//		template.convertAndSend("/logs/broadcast",data);
//		return "success";
//	}
	
//    @MessageMapping("/send")
//    @SendTo("/logs/updates")
//    public ResponseForm<?> send(String log) {
//    	
//    	ResponseForm<?> response = new ResponseForm<>();
//    	response.setStatus(true);
//    	try {
//    		queue.send(log);
//    	} catch( Exception e) {
//    		response.setStatus(false);
//    	}
//    	
//        return response;
//    }
}
