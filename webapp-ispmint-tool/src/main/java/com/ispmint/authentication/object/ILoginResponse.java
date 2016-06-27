package com.ispmint.authentication.object;

public interface ILoginResponse {

	public Integer getUserID();
	public String getName();
	public Integer getAvatar();
	public String getAvatarLink();
	public void setAvatarLink(String avatarLink);
}
