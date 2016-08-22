package com.fingerprint.urltagging.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fingerprint.keymanagement.mapper.KeyMapper;
import com.fingerprint.keymanagement.mapper.ValueMapper;
import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.keymanagement.object.ValueForm;
import com.fingerprint.object.ResponsePaginationForm;
import com.fingerprint.urltagging.object.URLForm;
import com.fingerprint.urltagging.service.URLTaggingService;
import com.google.common.base.Strings;

import service.keymanagement.KeyManagementService;
import service.keymanagement.ValueManagementService;
import service.keymanagement.model.KeyModel;
import service.urlmanagement.URLManagementService;
import service.urlmanagement.model.URLModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.WebEventService;

public class URLTaggingServiceImpl implements URLTaggingService {

	private URLManagementService URLManagementService;
	private ValueManagementService valueManagementService;
	private WebEventService webEventService;
	private ValueMapper valueMapper;
	public URLTaggingServiceImpl(URLManagementService URLManagementService, ValueManagementService valueManagementService, WebEventService webEventService, ValueMapper valueMapper) {
		// TODO Auto-generated constructor stub
		this.valueManagementService = valueManagementService;
		this.webEventService = webEventService; 
		this.URLManagementService = URLManagementService;
		this.valueMapper = valueMapper;
	}
	
	@Override
	public ResponsePaginationForm<URLForm> getAllURLs(String url, String page, String limit) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLForm> response = new ResponsePaginationForm<>();
		response.setCount(webEventService.countDistincyByURL("%"+url+"%"));
		Long end = (Long.valueOf(limit)+1)*Long.valueOf(page);
		Long start = (end-(Long.valueOf(limit)+1));
		List<WebEventModel> webEvents = webEventService.getAllDistinctByURL("%"+url+"%", start, end);
	    List<URLForm> urls = new ArrayList<>();
	    for(WebEventModel webEvent: webEvents) {
	    	URLForm form = new URLForm();
	    	form.setPath(webEvent.getUrl());
	    	List<URLModel> urlModels = URLManagementService.getAll("%"+webEvent.getUrl());
	    	for(URLModel urlModel: urlModels){
		      KeyForm keyForm = new KeyForm();
		      keyForm.setKey(urlModel.gettKey());
		      if(!Strings.isNullOrEmpty(urlModel.gettValues())) {
		    	  List<ValueForm> valueForms = new ArrayList<>();
		    	  for(String valueID: urlModel.gettValues().split(",")){
		    		  ValueForm valueForm = new ValueForm();
		    		  try{
		    			  valueForm  = valueMapper.unmarshall(valueManagementService.get(Long.valueOf(valueID)));
		    		  }catch(Exception e){
		    			  valueForm.setValue(valueID);
		    		  }
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

	@Override
	public ResponsePaginationForm<URLForm> save(List<URLForm> urlForms) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLForm> response = new ResponsePaginationForm<>();
		for(URLForm urlForm: urlForms){
			URLManagementService.deleteByURL("%"+urlForm.getPath());
			for(KeyForm keyForm: urlForm.getKeys()){
				URLModel urlModel = new URLModel();
				urlModel.setUrl(urlForm.getPath());
				urlModel.settKey(keyForm.getKey());
				if(keyForm.getValues().size() > 0){
					List<String> values = new ArrayList<String>();
					for(ValueForm valueForm: keyForm.getValues()){
						if(!Strings.isNullOrEmpty(valueForm.getId())) {
							values.add(valueForm.getId());
						} else {
							values.add(valueForm.getValue());
						}
					}
					urlModel.settValues(String.join(",", values));
				}
				URLManagementService.save(urlModel);
			}
		}
	
		
		return response;
	}

}
