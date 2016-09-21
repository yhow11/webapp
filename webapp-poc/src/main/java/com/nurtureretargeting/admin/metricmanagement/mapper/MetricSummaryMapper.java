package com.nurtureretargeting.admin.metricmanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.nurtureretargeting.admin.metricmanagement.object.MetricSummaryForm;

import common.mapper.ListMapper;
import service.metricmanagement.model.MetricSummaryModel;

public class MetricSummaryMapper implements ListMapper<MetricSummaryModel, MetricSummaryForm> {

	@Override
	public MetricSummaryModel marshall(MetricSummaryForm param) throws Exception {
		// TODO Auto-generated method stub
		return marshall(param, new MetricSummaryModel());
	}

	@Override
	public MetricSummaryForm unmarshall(MetricSummaryModel param) throws Exception {
		// TODO Auto-generated method stub
		return unmarshall(param, new MetricSummaryForm());
	}

	@Override
	public MetricSummaryModel marshall(MetricSummaryForm e, MetricSummaryModel target) throws Exception {
		// TODO Auto-generated method stub
		target.setVISITORID(e.getVisitorID());
		target.setMETRICNAME(e.getMetricName());
		target.setMETRICTYPE(e.getMetricType());
		target.setTVALUES(e.getValues());
		return target;
	}

	@Override
	public MetricSummaryForm unmarshall(MetricSummaryModel t, MetricSummaryForm target) throws Exception {
		// TODO Auto-generated method stub
		target.setVisitorID(t.getVISITORID());
		target.setMetricName(t.getMETRICNAME());
		target.setMetricType(t.getMETRICTYPE());
		target.setValues(t.getTVALUES());
		return target;
	}

	@Override
	public List<MetricSummaryModel> marshall(List<MetricSummaryForm> e) throws Exception {
		// TODO Auto-generated method stub
		List<MetricSummaryModel> models=  new ArrayList<>();
		for(MetricSummaryForm form: e){
			models.add(marshall(form));
		}
		return models;
	}

	@Override
	public List<MetricSummaryForm> unmarshall(List<MetricSummaryModel> t) throws Exception {
		// TODO Auto-generated method stub
		List<MetricSummaryForm> forms=  new ArrayList<>();
		for(MetricSummaryModel model: t){
			forms.add(unmarshall(model));
		}
		return forms;
	}

}
