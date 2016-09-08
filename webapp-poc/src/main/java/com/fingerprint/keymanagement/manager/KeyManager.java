package com.fingerprint.keymanagement.manager;

import com.fingerprint.keymanagement.mapper.KeyMapper;
import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.object.ResponseForm;

import service.keymanagement.KeyManagementService;

public class KeyManager {

	private KeyMapper keyMapper;
	private KeyManagementService keyManagementService;
	public KeyManager(KeyMapper keyMapper, KeyManagementService keyManagementService) {
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
	
	public ResponseForm<KeyForm> getAll(String offset, String limit) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		response.getData().addAll(keyMapper.unmarshall(keyManagementService.getAll(Long.valueOf(offset), Long.valueOf(limit))));
		return response;
	}
	
	public boolean checkExists(String key) throws Exception{
		if(keyManagementService.get(key) != null){
			return true;
		}
		return false;
	}
	
	public ResponseForm<KeyForm> delete(String key) throws Exception{
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		keyManagementService.remove(key);
		return response;
	}
}
