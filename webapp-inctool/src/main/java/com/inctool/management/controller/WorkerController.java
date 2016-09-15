package com.inctool.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inctool.common.form.ResponseForm;
import com.inctool.management.form.WorkerForm;
import com.inctool.management.manager.WorkerManager;

import common.query.form.FormParam;
import common.query.form.FormResponse;

@Controller
@RequestMapping("api/worker")
public class WorkerController {

	@Autowired
	private WorkerManager workerManager;
	
	@RequestMapping(value = "getAll", method = RequestMethod.POST)
    public @ResponseBody FormResponse<WorkerForm> getAll(@RequestBody FormParam<WorkerForm> param) throws Exception {
        return workerManager.getAll(param);
    }
	@RequestMapping(value = "remove", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<WorkerForm> remove(@RequestParam("id") String id) {
       
        ResponseForm<WorkerForm> response = new ResponseForm<WorkerForm>();
        response.setMessage("SUCCESS");
        response.setStatus(true);
        try {
//        	memberService.remove(id);
        } catch (Exception e) {
        	response.setMessage("FAILED");
        	 response.setStatus(false);
        }
        return response;
    }
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public @ResponseBody FormResponse<WorkerForm> get(@RequestBody WorkerForm workerForm) throws Exception {
       
		FormResponse<WorkerForm> response = new FormResponse<>();
        response.getData().add(workerManager.save(workerForm));
        response.setStatus(true);
        return response;
    }
}
