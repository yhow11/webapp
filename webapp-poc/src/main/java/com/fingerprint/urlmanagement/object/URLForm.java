package com.fingerprint.urlmanagement.object;

import java.util.ArrayList;
import java.util.List;

import com.fingerprint.keymanagement.object.KeyForm;

public class URLForm {

	private String url;
	private String path;
	private List<KeyForm> keys = new ArrayList<>();
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<KeyForm> getKeys() {
		return keys;
	}
	public void setKeys(List<KeyForm> keys) {
		this.keys = keys;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
