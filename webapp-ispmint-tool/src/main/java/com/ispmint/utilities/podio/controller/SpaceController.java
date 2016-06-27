package com.ispmint.utilities.podio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispmint.authentication.form.AuthenticationForm;
import com.ispmint.common.form.ResponseForm;
import com.ispmint.utilities.podio.service.IPodioService;
import com.podio.org.OrganizationWithSpaces;

@Controller
@RequestMapping("space")
public class SpaceController {

	@Autowired
	IPodioService podioService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public @ResponseBody ResponseForm<OrganizationWithSpaces> getAll(@RequestParam(value="orgID", required=false) String orgID) throws Exception {
		List<OrganizationWithSpaces> orgs = podioService.getOrganization();
		ResponseForm<OrganizationWithSpaces> response = new ResponseForm<OrganizationWithSpaces>();
		response.setMessage("SUCCESS");
		response.setStatus(true);
		response.setData(orgs);
		return response;
	}
}
