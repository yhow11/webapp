package com.ispmint.authentication.object.impl;

import com.ispmint.authentication.object.ILoginResponse;

public class LoginResponseImpl implements ILoginResponse {
	
	private Integer userID;
	private String name;
	private Integer avatar;
	private String avatarLink;
	
	@Override
	public Integer getUserID() {
		// TODO Auto-generated method stub
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getAvatar() {
		// TODO Auto-generated method stub
		return avatar;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	@Override
	public String getAvatarLink() {
		// TODO Auto-generated method stub
		return avatarLink;
	}

	@Override
	public void setAvatarLink(String avatarLink) {
		// TODO Auto-generated method stub
		this.avatarLink = avatarLink;
	}
}
