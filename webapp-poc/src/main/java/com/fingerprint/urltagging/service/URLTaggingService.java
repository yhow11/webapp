package com.fingerprint.urltagging.service;

import java.util.List;

import com.fingerprint.object.ResponsePaginationForm;
import com.fingerprint.urltagging.object.URLForm;

public interface URLTaggingService {
	
	public ResponsePaginationForm<URLForm> getAllURLs(String url, String page, String limit) throws Exception;
	public ResponsePaginationForm<URLForm> save(List<URLForm> urlForms) throws Exception;
}
