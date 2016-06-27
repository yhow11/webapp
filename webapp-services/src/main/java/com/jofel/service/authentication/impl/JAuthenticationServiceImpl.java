package com.jofel.service.authentication.impl;

import com.jofel.service.authentication.JAuthenticationService;
import com.jofel.service.authentication.response.JLoginResponse;
import com.jofel.service.common.response.JServiceResponse.ResponseType;
import com.jofel.service.management.JUserService;
import com.jofel.service.management.param.JUserParam;
import com.jofel.service.management.response.JUserResponse;

public class JAuthenticationServiceImpl implements JAuthenticationService {
	
	private JUserService userService;
	
	public JAuthenticationServiceImpl(JUserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}
	
	public JLoginResponse login(String username, String password) {
		// TODO Auto-generated method stub
		JLoginResponse response = new JLoginResponse();
		
		JUserParam param = new JUserParam();
		JUserResponse userResponse = userService.findOne(param);
		if(ResponseType.SUCCESS.equals(userResponse.getStatus())) {
			response.setStatus(userResponse.getStatus());
			response.setData(userResponse.getData());
		} else {
			response.setStatus(ResponseType.FAILED);
		}
		return response;
	}

	public JLoginResponse logout(String username, String password) {
		// TODO Auto-generated method stub
		JLoginResponse response = new JLoginResponse();
		JUserParam param = new JUserParam();
		JUserResponse userResponse = userService.findOne(param);
		if(ResponseType.SUCCESS.equals(userResponse.getStatus())) {
			response.setStatus(userResponse.getStatus());
			response.setData(userResponse.getData());
		} else {
			response.setStatus(ResponseType.FAILED);
		}
		return response;
	}

}
