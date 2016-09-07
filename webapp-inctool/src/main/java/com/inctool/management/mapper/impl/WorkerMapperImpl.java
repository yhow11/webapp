package com.inctool.management.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.inctool.management.form.WorkerForm;
import com.inctool.management.mapper.WorkerMapper;

import common.mapper.util.MapUtil;
import service.membermanagement.model.INCWorkerModel;

public class WorkerMapperImpl implements WorkerMapper {

	@Override
	public List<INCWorkerModel> marshal(List<WorkerForm> e) throws Exception {
		// TODO Auto-generated method stub
		List<INCWorkerModel> models = new ArrayList<>();
		for(WorkerForm form: e){
			models.add(MapUtil.map(INCWorkerModel.class, form));
		}
		return models;
	}

	@Override
	public List<WorkerForm> unmarshal(List<INCWorkerModel> t) throws Exception {
		// TODO Auto-generated method stub
		List<WorkerForm> forms = new ArrayList<>();
		for(INCWorkerModel model: t){
			forms.add(MapUtil.map(WorkerForm.class, model));
		}
		return forms;
	}

	@Override
	public INCWorkerModel marshal(WorkerForm e) throws Exception {
		// TODO Auto-generated method stub
		if(e != null) {
			INCWorkerModel model = MapUtil.map(INCWorkerModel.class, e);
			return model;
		} else {
			return null;
		}
		
	}

	@Override
	public WorkerForm unmarshal(INCWorkerModel t) throws Exception {
		// TODO Auto-generated method stub
		if(t != null) {
			return MapUtil.map(WorkerForm.class, t);
		} else {
			return null;
		}
	}


}
