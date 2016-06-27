package com.ispmint.authentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispmint.authentication.form.AuthenticationForm;
import com.ispmint.authentication.object.ILoginResponse;
import com.ispmint.authentication.service.IAuthenticationService;
import com.ispmint.common.form.ResponseForm;

@Controller
@RequestMapping("authentication")
public class AuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody ResponseForm<ILoginResponse> loginPost(
			@RequestBody AuthenticationForm authenticationForm, HttpSession session) throws Exception {
		ResponseForm<ILoginResponse> response = new ResponseForm<ILoginResponse>();
		
		ILoginResponse loginResponse = null;
		try{
			loginResponse = authenticationService.login(authenticationForm);
			response.getData().add(loginResponse);
			response.setMessage("SUCCESS");
			response.setStatus(true);
		} catch(Exception e) {
			response.setMessage("Invalid credentials!");
			response.setStatus(false);
		}
		
		
		if(response.isStatus()) {
			session.setAttribute("user", loginResponse);
		}
		return response;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public @ResponseBody ResponseForm logoutGet(HttpServletRequest request, HttpServletResponse response) {
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(
				AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		cookieClearingLogoutHandler.logout(request, response, null);
		securityContextLogoutHandler.logout(request, response, null);
		ResponseForm result = new ResponseForm();
		result.setMessage("SUCCESS");
		result.setStatus(true);
		return result;
	}
}
