package com.fingerprint.keymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.keymanagement.service.KeyValueManagementService;
import com.fingerprint.object.ResponseForm;


@Controller
public class KeyManagementController {

	@Autowired
	private KeyValueManagementService keyValueManagementService;
	
	
	@RequestMapping(value = "keymanagement/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<KeyForm> save(@RequestBody KeyForm keyForm) throws Exception {
		return keyValueManagementService.save(keyForm);
	}
	
	@RequestMapping(value = "keymanagement/getAllByPagination", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<KeyForm> getAllByPagination(@RequestParam(name="start") String start, @RequestParam(name="end") String end, @RequestParam(name="value") String value) throws Exception {
		return keyValueManagementService.getAll(value, start, end);
	}
	@RequestMapping(value = "keymanagement/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<KeyForm> getAll(@RequestParam(name="key") String key) throws Exception {
		return keyValueManagementService.getAll(key);
	}
	@RequestMapping(value = "keymanagement/getByKey", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<KeyForm> getByKey(@RequestParam(name="key") String key) throws Exception {
		return keyValueManagementService.getByKey(key);
	}
	
	@RequestMapping(value = "keymanagement/delete", method = RequestMethod.DELETE)
	public @ResponseBody  ResponseForm<KeyForm> delete(@RequestParam(name="key") String key) throws Exception {
		return keyValueManagementService.delete(key);
	}
}
