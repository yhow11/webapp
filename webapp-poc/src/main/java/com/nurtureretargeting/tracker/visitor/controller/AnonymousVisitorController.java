package com.nurtureretargeting.tracker.visitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.tracker.visitor.builder.AnonymousVisitorDetailsBuilder;
import com.nurtureretargeting.tracker.visitor.object.AnonymousVisitorDetail;
import com.nurtureretargeting.tracker.visitor.object.FingerPrintDetail;

import common.form.ResponseForm;

@Controller
public class AnonymousVisitorController {
	
	@Autowired
	private AnonymousVisitorDetailsBuilder anonymousVisitorDetailsBuilder;

	@RequestMapping(value = "anonymousvisitor/getDetail", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<AnonymousVisitorDetail> getDetail(@RequestBody FingerPrintDetail detail) throws Exception {
		
		ResponseForm<AnonymousVisitorDetail> response =  new ResponseForm<AnonymousVisitorDetail>();
		response.setStatus(true);
		response.getData().add(anonymousVisitorDetailsBuilder.build(detail.getSessionID(), detail.getBrowserFP()));
	
		return response;
	}
}
