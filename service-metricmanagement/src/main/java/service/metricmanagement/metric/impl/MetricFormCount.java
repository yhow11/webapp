package service.metricmanagement.metric.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.mapper.Mapper;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.metricmanagement.metric.form.MetricForm;
import service.metricmanagement.metric.model.MetricModel;

@Service("MetricFormCount")
@Transactional
public class MetricFormCount implements Count<MetricForm> {

	@Resource(name="${MetricFormCount.aggregator}")
	private Count<MetricModel> aggregator;
	@Resource(name="${MetricFormCount.mapper}")
	private Mapper<MetricModel, MetricForm> mapper;
	
	public MetricFormCount() {
	}

	@Override
	public Long count(Param<MetricForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(mapper.marshall(param, new DefaultParam<>(MetricModel.class)));
	}


}
