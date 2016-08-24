//package com.fingerprint.keymanagement.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.fingerprint.keymanagement.mapper.ValueMapper;
//import com.fingerprint.keymanagement.object.ValueForm;
//import com.fingerprint.object.ResponseForm;
//
//import service.keymanagement.ValueManagementService;
//
//
//@Controller
//public class ValueManagementController {
//
//	@Autowired
//	private ValueManagementService valueManagementService;
//	
//	@Autowired
//	private ValueMapper valueMapper;
//	
//	
//	@RequestMapping(value = "valuemanagement/getAll", method = RequestMethod.GET)
//	public @ResponseBody  ResponseForm<ValueForm> getAll(@RequestParam(name="key") String key) throws Exception {
//		ResponseForm<ValueForm>  response= new ResponseForm<ValueForm>();
//		response.getData().addAll(valueMapper.unmarshall(valueManagementService.getAll("%"+key)));
//		response.setStatus(true);
//		return response;
//	}
//}
