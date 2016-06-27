package com.jofel.service.authentication;

import com.jofel.service.authentication.response.JLoginResponse;

public interface JAuthenticationService {

	public JLoginResponse login(String username, String password);
	public JLoginResponse logout(String username, String password);
}
