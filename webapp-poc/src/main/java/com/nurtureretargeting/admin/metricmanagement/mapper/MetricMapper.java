package com.nurtureretargeting.admin.metricmanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;
import com.nurtureretargeting.admin.metricmanagement.object.MetricForm;

import common.mapper.ListMapper;
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
		target.setTKEY(e.getKey());
		target.setNAME(e.getName());
		target.setTYPE(e.getType());
		return target;
	}

	@Override
	public MetricForm unmarshall(MetricModel t, MetricForm target) throws Exception {
		// TODO Auto-generated method stub
		target.setId(String.valueOf(t.getID()));
		target.setKey(t.getTKEY());
		target.setName(t.getNAME());
		target.setType(t.getTYPE());
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
