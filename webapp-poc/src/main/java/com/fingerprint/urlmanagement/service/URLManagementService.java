package com.fingerprint.urlmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.httpclient.util.URIUtil;

import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.keymanagement.object.ValueForm;
import com.fingerprint.object.ResponsePaginationForm;
import com.fingerprint.urlmanagement.object.URLForm;
import com.fingerprint.urlmanagement.object.URLImportForm;
import com.google.common.base.Strings;

import service.urlmanagement.URLService;
import service.urlmanagement.model.URLImportModel;
import service.urlmanagement.model.URLTaggedModel;

public class URLManagementService {

	private URLService<URLImportModel> URLImportService;
	private URLService<URLTaggedModel> URLTaggedService;
	private URLService<URLImportModel> URLSiteMapService;
	public URLManagementService(URLService<URLImportModel> URLImportService, URLService<URLTaggedModel> URLTaggedService, URLService<URLImportModel> URLSiteMapService) {
		// TODO Auto-generated constructor stub
		this.URLImportService = URLImportService;
		this.URLTaggedService = URLTaggedService;
		this.URLSiteMapService = URLSiteMapService;
	}
	public ResponsePaginationForm<URLForm> getAllTaggedURLs(String url, String page, String limit) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLForm> response = new ResponsePaginationForm<>();
		response.setCount(URLImportService.getCount("%"+url+"%"));
		Long limitNum = (Long.valueOf(limit)+1);
		Long end = limitNum*Long.valueOf(page);
		Long start = (end-limitNum);
		List<URLImportModel> urlImportModels = URLImportService.getAll("%"+url+"%", start, limitNum);
	    List<URLForm> urls = new ArrayList<>();
	    for(URLImportModel urlImportModel: urlImportModels) {
	    	URLForm form = new URLForm();
	    	form.setPath(URIUtil.getFromPath(urlImportModel.getUrl()));
	    	form.setUrl(urlImportModel.getUrl());
	    	List<URLTaggedModel> urlModels = URLTaggedService.getAll("%"+urlImportModel.getUrl());
	    	for(URLTaggedModel urlModel: urlModels){
		      KeyForm keyForm = new KeyForm();
		      keyForm.setKey(urlModel.gettKey());
		      if(!Strings.isNullOrEmpty(urlModel.gettValues())) {
		    	  List<ValueForm> valueForms = new ArrayList<>();
		    	  for(String value: urlModel.gettValues().split(",")){
		    		  ValueForm valueForm = new ValueForm();
		    		  valueForm.setId(UUID.randomUUID().toString());
		    		  valueForm.setKey(urlModel.gettKey());
		    		  valueForm.setValue(value);
		    		  valueForms.add(valueForm);
		    	  }
		    	  keyForm.setValues(valueForms);
		      }
		      form.getKeys().add(keyForm);
	    	}
	    	
	    	urls.add(form);
	    }
	    response.getData().addAll(urls);
	    
		return response;
	}
	public ResponsePaginationForm<URLImportForm> getAllSiteMapURLs(String url) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLImportForm> response = new ResponsePaginationForm<>();
		List<URLImportModel> importURLs = URLSiteMapService.getAll(url);
		for(URLImportModel importURL: importURLs){
			URLImportForm urlImportForm = new URLImportForm();
			urlImportForm.setUrl(importURL.getUrl());
		    response.getData().add(urlImportForm);
		}
		response.setStatus(true);
	    
		return response;
	}
	public ResponsePaginationForm<URLImportForm> saveImportURLs(List<URLImportForm> urlForms) throws Exception {
		for(URLImportForm urlForm: urlForms){
			URLImportModel urlImportModel = new URLImportModel();
			urlImportModel.setUrl(urlForm.getUrl());
			URLImportService.save(urlImportModel);
		}
		
		ResponsePaginationForm<URLImportForm> response = new ResponsePaginationForm<URLImportForm>();
		response.setStatus(true);
		response.getData().addAll(urlForms);
		return response;
	}
	public ResponsePaginationForm<URLForm> saveTaggedURLs(List<URLForm> urlForms) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLForm> response = new ResponsePaginationForm<>();
		for(URLForm urlForm: urlForms){
			URLTaggedService.delete("%"+urlForm.getUrl());
			for(KeyForm keyForm: urlForm.getKeys()){
				URLTaggedModel urlModel = new URLTaggedModel();
				urlModel.setUrl(urlForm.getUrl());
				urlModel.settKey(keyForm.getKey());
				if(keyForm.getValues().size() > 0){
					List<String> values = new ArrayList<String>();
					for(ValueForm valueForm: keyForm.getValues()){
						values.add(valueForm.getValue());
					}
					urlModel.settValues(String.join(",", values));
				}
				URLTaggedService.save(urlModel);
			}
		}
	
		
		return response;
	}
}
