package com.nurtureretargeting.tracker.session.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.tracker.session.manager.SessionManager;

import common.form.ResponseForm;
import usertracker.browser.model.SessionModel;

@Controller
public class SessionController {

	@Autowired
	private SessionManager sessionManager;
	

	@RequestMapping(value = "session/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<SessionModel> getAll(@RequestParam("sessionID") String sessionID, @RequestParam("browserFP") String browserFP) throws Exception {
		
		ResponseForm<SessionModel> response =  new ResponseForm<SessionModel>();
		List<SessionModel> sessions = sessionManager.getAll(sessionID, browserFP);
		
		if(sessions != null) {
			response.setStatus(true);
			response.setData(sessions);
		} else {
			response.setStatus(false);
	    	response.setMessage(ResponseForm.NO_DATA);
		}
		return response;
	}
}
