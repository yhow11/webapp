package com.inctool.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.RequestForm;
import common.form.ResponseForm;
import common.orm.command.TService;
import common.form.Page;
import inc.member.form.WorkerForm;
import inc.member.service.WorkerCommandService;
import inc.member.service.WorkerQueryService;
import inc.member.service.WorkerService;

@Controller
@RequestMapping("api/worker")
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
    public @ResponseBody Page<WorkerForm> getAll(@RequestBody RequestForm<WorkerForm> requestForm) throws Exception {
		return workerService.query().getAllWithPagination(requestForm);
    }
	@RequestMapping(value = "get", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<WorkerForm> get(@RequestParam("id") String id) throws Exception {
        return new ResponseForm<>( workerService.query().get(id));
    }
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<WorkerForm> remove(@RequestParam("id") String id) {
       
        try {
        	workerService.command().remove(workerService.query().get(id));
        } catch (Exception e) {
        	return new ResponseForm<>(false, "FAILED");
        }
        return new ResponseForm<>();
    }
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<WorkerForm> get(@RequestBody WorkerForm workerForm) throws Exception {
		return new ResponseForm<>(workerService.command().save(workerForm));
    }
}
