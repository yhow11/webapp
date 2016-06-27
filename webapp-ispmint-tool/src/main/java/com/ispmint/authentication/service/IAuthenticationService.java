package com.ispmint.authentication.service;

import com.ispmint.authentication.object.ILoginRequest;
import com.ispmint.authentication.object.ILoginResponse;

public interface IAuthenticationService {
	
	public ILoginResponse login(ILoginRequest request)  throws Exception ;

}
