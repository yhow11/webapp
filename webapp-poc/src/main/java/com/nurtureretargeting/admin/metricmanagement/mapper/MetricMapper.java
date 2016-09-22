package com.nurtureretargeting.admin.metricmanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;
import com.nurtureretargeting.admin.keymanagement.object.KeyForm;
import com.nurtureretargeting.admin.metricmanagement.object.MetricForm;
import com.nurtureretargeting.admin.metricmanagement.object.MetricTypeForm;

import common.mapper.ListMapper;
import service.metricmanagement.enums.MetricTypeEnum;
import service.metricmanagement.model.MetricModel;

public class MetricMapper implements ListMapper<MetricModel, MetricForm> {

	@Override
	public MetricModel marshall(MetricForm param) throws Exception {
		// TODO Auto-generated method stub
		return marshall(param, new MetricModel());
	}

	@Override
	public MetricForm unmarshall(MetricModel param) throws Exception {
		// TODO Auto-generated method stub
		return unmarshall(param, new MetricForm());
	}

	@Override
	public MetricModel marshall(MetricForm e, MetricModel target) throws Exception {
		// TODO Auto-generated method stub
		if(!Strings.isNullOrEmpty(e.getId())){
			target.setID(Long.valueOf(e.getId()));
		}
		target.setNAME(e.getName());
		
		if(e.getKeys().size() > 0) {
			target.setTKEY(e.getKeys().get(0).getKey());
		}
		if(e.getTypes().size() > 0) {
			target.setTYPE(e.getTypes().get(0).getType());
		}
		return target;
	}

	@Override
	public MetricForm unmarshall(MetricModel t, MetricForm target) throws Exception {
		// TODO Auto-generated method stub
		target.setId(String.valueOf(t.getID()));
		target.setName(t.getNAME());
		
		if(!Strings.isNullOrEmpty(t.getTKEY())){
			KeyForm keyForm = new KeyForm();
			keyForm.setKey(t.getTKEY());
			target.getKeys().add(keyForm);
		}
		if(!Strings.isNullOrEmpty(t.getTYPE())){
			MetricTypeForm metricTypeForm = new MetricTypeForm();
			metricTypeForm.setType(t.getTYPE());
			metricTypeForm.setName(MetricTypeEnum.getByType(t.getTYPE()).getName());
			target.getTypes().add(metricTypeForm);
		}
		return target;
	}

	@Override
	public List<MetricModel> marshall(List<MetricForm> e) throws Exception {
		// TODO Auto-generated method stub
		List<MetricModel> models=  new ArrayList<>();
		for(MetricForm metricForm: e){
			models.add(marshall(metricForm));
		}
		return models;
	}

	@Override
	public List<MetricForm> unmarshall(List<MetricModel> t) throws Exception {
		// TODO Auto-generated method stub
		List<MetricForm> forms=  new ArrayList<>();
		for(MetricModel metricModel: t){
			forms.add(unmarshall(metricModel));
		}
		return forms;
	}

}
