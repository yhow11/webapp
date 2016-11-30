package com.nurtureretargeting.admin.visitormanagement.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.LogMetaData;
import usertracker.browser.visitor.AnonymousVisitorKeeper;
import usertracker.browser.visitor.model.VisitorModel;

@Controller
public class VisitorController {

	@Resource(name="${VisitorController.keeper}")
	private AnonymousVisitorKeeper keeper;
	
	
	@RequestMapping(value = "visitor/get", method = RequestMethod.GET)
	public @ResponseBody  VisitorModel getPage(@RequestParam("sessionID") String sessionID,
			@RequestParam("browserFP") String browserFP) throws Exception {
		return keeper.get(sessionID, browserFP, new LogMetaData(""));
	}
	
}
