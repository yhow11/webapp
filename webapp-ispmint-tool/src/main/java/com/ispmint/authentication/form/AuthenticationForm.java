package com.ispmint.authentication.form;

import com.ispmint.authentication.object.ILoginRequest;

public class AuthenticationForm implements ILoginRequest{
	
	private String username;
	private String password;
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
