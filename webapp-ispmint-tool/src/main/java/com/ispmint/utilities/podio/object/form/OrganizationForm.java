package com.ispmint.utilities.podio.object.form;

import java.util.ArrayList;
import java.util.List;

public class OrganizationForm {

	private Integer id;
	private String name = "";
	private String url = "";
	private Integer logo;
	private List<SpaceForm> spaces = new ArrayList<SpaceForm>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SpaceForm> getSpaces() {
		return spaces;
	}
	public void setSpaces(List<SpaceForm> spaces) {
		this.spaces = spaces;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getLogo() {
		return logo;
	}
	public void setLogo(Integer logo) {
		this.logo = logo;
	}
	
	
}
