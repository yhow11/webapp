package com.nurtureretargeting.site.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import common.form.ResponseForm;
import usertracker.browser.service.VisitorLogService;

@Controller
public class SiteController {

	@Autowired
	private VisitorLogService visitorLogService;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ModelAndView getUsersView() {
		ModelAndView mv = new ModelAndView("template");
		return mv;
	}

	@RequestMapping(value = "site", method = RequestMethod.GET)
	public String view() throws Exception {
		return "index";
	}

	@RequestMapping(value = "wow", method = RequestMethod.GET)
	public String wow() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8080/webapp-poc/notifyEvents", String.class);
		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			Authentication authentication, @RequestParam(defaultValue = "") String modal) throws Exception {

		return "commons/template";
	}
	
	@RequestMapping(value = "/currentTime", method = RequestMethod.GET)
	public @ResponseBody ResponseForm<String> currentTime() throws Exception {
		ResponseForm<String> response = new ResponseForm<>();
		response.getData().add(String.valueOf(new Date().getTime()));
		response.setStatus(true);
		return response;
		
	}
}
