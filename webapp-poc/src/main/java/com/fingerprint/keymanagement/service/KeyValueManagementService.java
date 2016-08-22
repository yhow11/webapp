package com.fingerprint.keymanagement.service;

import java.util.List;

import com.fingerprint.keymanagement.mapper.KeyMapper;
import com.fingerprint.keymanagement.mapper.ValueMapper;
import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.keymanagement.object.ValueForm;
import com.fingerprint.object.ResponseForm;

import service.keymanagement.KeyManagementService;
import service.keymanagement.ValueManagementService;

public class KeyValueManagementService {

	private KeyMapper keyMapper;
	private ValueMapper valueMapper;
	private KeyManagementService keyManagementService;
	private ValueManagementService valueManagementService;
	public KeyValueManagementService(KeyMapper keyMapper, ValueMapper valueMapper, KeyManagementService keyManagementService, ValueManagementService valueManagementService) {
		// TODO Auto-generated constructor stub
		this.keyMapper = keyMapper;
		this.valueMapper = valueMapper;
		this.keyManagementService = keyManagementService;
		this.valueManagementService = valueManagementService;
		
	}
	
	public ResponseForm<KeyForm> save(KeyForm keyForm) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		for(ValueForm valueForm: keyForm.getValues()){
			valueForm = valueMapper.unmarshall(valueManagementService.save(valueMapper.marshall(valueForm)));
		}
		keyForm = keyMapper.unmarshall(keyManagementService.save(keyMapper.marshall(keyForm)));
		
		response.getData().add(keyForm);
		return response;
	}
	
	public ResponseForm<KeyForm> getAllByPagination(String start, String end) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		List<KeyForm> keyForms = keyMapper.unmarshall(keyManagementService.getAll(Long.valueOf(start), Long.valueOf(end)));
		for(KeyForm keyForm: keyForms){
			 keyForm.setValues(valueMapper.unmarshall(valueManagementService.getAll(keyForm.getKey())));
		}
		response.getData().addAll(keyForms);
		return response;
	}
	
	public ResponseForm<KeyForm> getAll(String key) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		List<KeyForm> keyForms = keyMapper.unmarshall(keyManagementService.getAll(key));
		for(KeyForm keyForm: keyForms){
			 keyForm.setValues(valueMapper.unmarshall(valueManagementService.getAll(keyForm.getKey())));
		}
		response.getData().addAll(keyForms);
		return response;
	}
	
	public ResponseForm<KeyForm> getByKey(String key) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		
		if(keyManagementService.get(key) != null){
			response.setStatus(false);
		} else {
			response.setStatus(true);
		}
		return response;
	}
	
	public ResponseForm<KeyForm> delete(String key) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		List<ValueForm> valueForms = valueMapper.unmarshall(valueManagementService.getAll(key));
		for(ValueForm valueForm : valueForms) {
			valueManagementService.delete(Long.valueOf(valueForm.getId()));
		}
		keyManagementService.delete(key);
		
		return response;
	}
}
