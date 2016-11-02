package service.metricmanagement.metricsummary.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.mapper.Mapper;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.metricmanagement.metricsummary.form.MetricSummaryForm;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

@Service("MetricSummaryFormCount")
@Transactional
public class MetricSummaryFormCount implements Count<MetricSummaryForm> {

	@Resource(name="${MetricSummaryFormCount.aggregator}")
	private Count<MetricSummaryModel> aggregator;
	@Resource(name="${MetricSummaryFormCount.mapper}")
	private Mapper<MetricSummaryModel, MetricSummaryForm> mapper;
	
	public MetricSummaryFormCount() {
	}

	@Override
	public Long count(Param<MetricSummaryForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(mapper.marshall(param, new DefaultParam<>(MetricSummaryModel.class)));
	}


}
