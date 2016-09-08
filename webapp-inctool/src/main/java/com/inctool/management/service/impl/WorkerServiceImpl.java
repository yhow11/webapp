package com.inctool.management.service.impl;

import com.inctool.management.form.WorkerForm;
import com.inctool.management.mapper.WorkerMapper;
import com.inctool.management.service.WorkerService;

import common.query.QueryParam;
import common.query.form.FormParam;
import common.query.form.FormResponse;
import service.membermanagement.model.INCWorkerModel;
import service.membermanagement.service.INCWorkerService;

public class WorkerServiceImpl implements WorkerService {

	private INCWorkerService iNCWorkerService;
	private WorkerMapper workerMapper;
	
	public WorkerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	public WorkerServiceImpl(INCWorkerService iNCWorkerService, WorkerMapper workerMapper) {
		// TODO Auto-generated constructor stub
		this.iNCWorkerService = iNCWorkerService;
		this.workerMapper = workerMapper;
	}
	@Override
	public WorkerForm save(WorkerForm workerForm) throws Exception {
		// TODO Auto-generated method stub
		return workerMapper.unmarshall(iNCWorkerService.save(workerMapper.marshall(workerForm)));
	}
	@Override
	public WorkerForm get(String id)   throws Exception{
		// TODO Auto-generated method stub
		return workerMapper.unmarshall(iNCWorkerService.get(id));
	}
	@Override
	public void remove(String id)  throws Exception {
		// TODO Auto-generated method stub
		iNCWorkerService.remove(iNCWorkerService.get(id));
	}
	@Override
	public FormResponse<WorkerForm> getAll(FormParam<WorkerForm> param) throws Exception {
		// TODO Auto-generated method stub
		Long limit = Long.valueOf(param.getLimit());
		Long page = Long.valueOf(param.getPage());
		Long offset = (page*limit)-limit;
		INCWorkerModel model = workerMapper.marshall(param.getData());
		
		QueryParam<INCWorkerModel> queryParam = new QueryParam<>(INCWorkerModel.class);
		queryParam.setLimit(limit);
		
		queryParam.setOffset(offset);
		queryParam.setLimit(limit);
		queryParam.setModel(model);
		FormResponse<WorkerForm> response = new FormResponse<>();
		Long totalCount = iNCWorkerService.getCount(queryParam);
		response.setTotalCount(String.valueOf(totalCount));
		response.setData(workerMapper.unmarshall(iNCWorkerService.getAll(queryParam)));
		return response;
	}

}
