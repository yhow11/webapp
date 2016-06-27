package com.inctool.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {

	@RequestMapping(value = "users", method = RequestMethod.GET)
    public ModelAndView getUsersView() {
        ModelAndView mv= new ModelAndView("template");
        return mv;
    }
	
	@RequestMapping(value = "site", method = RequestMethod.GET)
	public String view() throws Exception {

		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			Authentication authentication, @RequestParam(defaultValue = "") String modal) throws Exception {

		return "commons/template";
	}
}
