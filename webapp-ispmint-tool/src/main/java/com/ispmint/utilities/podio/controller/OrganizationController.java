package com.ispmint.utilities.podio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispmint.common.form.ResponseForm;
import com.ispmint.utilities.podio.object.form.OrganizationForm;
import com.ispmint.utilities.podio.object.form.SpaceForm;
import com.ispmint.utilities.podio.service.IPodioService;
import com.podio.org.OrganizationWithSpaces;
import com.podio.space.SpaceMini;

@Controller
@RequestMapping("organization")
public class OrganizationController {

	@Autowired
	IPodioService podioService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public @ResponseBody ResponseForm<OrganizationForm> getAll() throws Exception {
		ResponseForm<OrganizationForm> response = new ResponseForm<OrganizationForm>();
		List<OrganizationWithSpaces> organizations =  podioService.getOrganization();
		for(OrganizationWithSpaces orgBean : organizations) {
			OrganizationForm orgForm = new OrganizationForm();
			orgForm.setId(orgBean.getId());
			orgForm.setName(orgBean.getName());
			orgForm.setLogo(orgBean.getLogo());
			orgForm.setUrl(orgBean.getUrl());
			for(SpaceMini space: orgBean.getSpaces()) {
				SpaceForm spaceForm = new SpaceForm();
				spaceForm.setName(space.getName());
				spaceForm.setId(space.getId());
				spaceForm.setUrl(space.getUrl());
				orgForm.getSpaces().add(spaceForm);
			}
			response.getData().add(orgForm);
		}
		response.setMessage("SUCCESS");
		response.setStatus(true);
		return response;
	}
}
