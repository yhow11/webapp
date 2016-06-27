package com.ispmint.authentication.service.impl;

import com.ispmint.authentication.object.ILoginRequest;
import com.ispmint.authentication.object.ILoginResponse;
import com.ispmint.authentication.object.impl.LoginResponseImpl;
import com.ispmint.authentication.service.IAuthenticationService;
import com.ispmint.utilities.podio.object.IPodioCredentials;
import com.ispmint.utilities.podio.object.PodioCredentialImpl;
import com.ispmint.utilities.podio.service.IPodioService;
import com.podio.contact.Profile;
import com.podio.file.File;

public class AuthenticationServiceImpl implements IAuthenticationService{
	
	private IPodioService podioService;

	public AuthenticationServiceImpl(IPodioService podioService) {
		// TODO Auto-generated constructor stub
		this.podioService = podioService;
	}
	

	@Override
	public ILoginResponse login(ILoginRequest loginRequest) throws Exception {
		// TODO Auto-generated method stub
		IPodioCredentials podioCredentials = new PodioCredentialImpl();
		podioCredentials.setPassword(loginRequest.getPassword());
		podioCredentials.setUsername(loginRequest.getUsername());
		podioService.setCredentials(podioCredentials);
		Profile profile = podioService.getProfile();
		File avatar = podioService.getFile(profile.getAvatar());
		LoginResponseImpl response = new LoginResponseImpl();
		response.setAvatar(profile.getAvatar());
		response.setAvatarLink(avatar.getLink());
		response.setName(profile.getName());
		response.setUserID(profile.getUserId());
		
		return response;
	}

}
