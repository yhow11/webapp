package com.fingerprint.keymanagement.service;

import com.fingerprint.keymanagement.mapper.KeyMapper;
import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.object.ResponseForm;

import common.query.util.QueryUtil;
import service.keymanagement.KeyManagementService;

public class KeyValueManagementService {

	private KeyMapper keyMapper;
	private KeyManagementService keyManagementService;
	public KeyValueManagementService(KeyMapper keyMapper, KeyManagementService keyManagementService) {
		// TODO Auto-generated constructor stub
		this.keyMapper = keyMapper;
		this.keyManagementService = keyManagementService;
		
	}
	
	public ResponseForm<KeyForm> save(KeyForm keyForm) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		keyForm = keyMapper.unmarshall(keyManagementService.save(keyMapper.marshall(keyForm)));
		
		response.getData().add(keyForm);
		return response;
	}
	
	public ResponseForm<KeyForm> getAll(String key, String offset, String limit) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		response.getData().addAll(keyMapper.unmarshall(keyManagementService.getAll(key, Long.valueOf(offset), Long.valueOf(limit))));
		return response;
	}
	
	public ResponseForm<KeyForm> getAll(String key) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		response.getData().addAll(keyMapper.unmarshall(keyManagementService.getAll(key)));
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
		keyManagementService.remove(key);
		return response;
	}
}
