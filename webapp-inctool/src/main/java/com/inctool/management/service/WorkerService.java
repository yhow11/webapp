package com.inctool.management.service;

import com.inctool.management.form.WorkerForm;

import common.query.form.FormParam;
import common.query.form.FormResponse;

public interface WorkerService {

	public void remove(String id) throws Exception;
	public WorkerForm save(WorkerForm workerForm) throws Exception;
	public WorkerForm get(String id) throws Exception;
	public FormResponse<WorkerForm> getAll(FormParam<WorkerForm> param) throws Exception;
}
