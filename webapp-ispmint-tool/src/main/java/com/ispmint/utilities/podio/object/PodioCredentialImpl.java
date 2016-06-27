package com.ispmint.utilities.podio.object;

public class PodioCredentialImpl implements IPodioCredentials {
	
	private String username;
	private String password;

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
		
	}

}
