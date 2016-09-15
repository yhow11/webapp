package com.nurtureretargeting.admin.keymanagement.manager;

import com.nurtureretargeting.admin.keymanagement.mapper.KeyMapper;
import com.nurtureretargeting.admin.keymanagement.object.KeyForm;

import common.form.ResponseForm;
import joptsimple.internal.Strings;
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
	
	public ResponseForm<KeyForm> getAll(String value, String offset, String limit) throws Exception{
		Long offsetLong = !Strings.isNullOrEmpty(offset)? Long.valueOf(offset):null;
		Long limitLong = !Strings.isNullOrEmpty(limit)? Long.valueOf(limit):null;
		value = !Strings.isNullOrEmpty(value)? value:null;
		ResponseForm<KeyForm> response =  new ResponseForm<KeyForm>();
		response.setStatus(true);
		response.getData().addAll(keyMapper.unmarshall(keyManagementService.getAll(value, offsetLong, limitLong)));
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
