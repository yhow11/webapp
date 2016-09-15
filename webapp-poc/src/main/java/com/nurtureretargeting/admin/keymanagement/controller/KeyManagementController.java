package com.nurtureretargeting.admin.keymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nurtureretargeting.admin.keymanagement.manager.KeyManager;
import com.nurtureretargeting.admin.keymanagement.object.KeyForm;

import common.form.ResponseForm;


@Controller
public class KeyManagementController {

	@Autowired
	private KeyManager keyManager;
	
	
	@RequestMapping(value = "keymanagement/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<KeyForm> save(@RequestBody KeyForm keyForm) throws Exception {
		return keyManager.save(keyForm);
	}
	
	@RequestMapping(value = "keymanagement/getAll", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<KeyForm> getAll(@RequestParam(name="value") String value, @RequestParam(name="start") String start, @RequestParam(name="end") String end) throws Exception {
		return keyManager.getAll(value, start, end);
	}
	@RequestMapping(value = "keymanagement/checkExists", method = RequestMethod.GET)
	public boolean checkExists(@RequestParam(name="key") String key) throws Exception {
		return keyManager.checkExists(key);
	}
	
	@RequestMapping(value = "keymanagement/delete", method = RequestMethod.DELETE)
	public @ResponseBody  ResponseForm<KeyForm> delete(@RequestParam(name="key") String key) throws Exception {
		return keyManager.delete(key);
	}
}