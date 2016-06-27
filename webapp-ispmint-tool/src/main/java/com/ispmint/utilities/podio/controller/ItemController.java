package com.ispmint.utilities.podio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispmint.common.form.ResponseForm;
import com.ispmint.utilities.podio.annotation.PodioApplication;
import com.ispmint.utilities.podio.object.item.TestAppItem;
import com.ispmint.utilities.podio.service.IPodioService;

@Controller
@RequestMapping("item")
public class ItemController {

	@Autowired
	IPodioService podioService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public @ResponseBody ResponseForm<TestAppItem> loginPost(@RequestParam(value="appID", required=false) String appID, @RequestParam(value="id", required=false) String id ) throws Exception {
		Map<Integer, Class<?>> appClasses = new HashMap<Integer, Class<?>>();
		appClasses.put(AnnotationUtils.findAnnotation(TestAppItem.class, PodioApplication.class).id(), TestAppItem.class);
		ResponseForm response = new ResponseForm<>();
		response.setMessage("SUCCESS");
		response.setStatus(true);
		response.getData().addAll(podioService.getItems(null, appClasses.get(Integer.valueOf(appID))));
		return response;
	}
}
