package service.metricmanagement.metricsummary.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.metricmanagement.metricsummary.form.MetricSummaryForm;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

@Service("MetricSummaryFormStorage")
@Transactional
public class MetricSummaryFormStorage implements Storage<MetricSummaryForm> {

	@Resource(name="${MetricSummaryFormStorage.storage}")
	private Storage<MetricSummaryModel> storage;
	@Resource(name="${MetricSummaryFormStorage.mapper}")
	private Mapper<MetricSummaryModel, MetricSummaryForm> mapper;
	
	public MetricSummaryFormStorage() {
	}
	@Override
	public List<MetricSummaryForm> get(Param<MetricSummaryForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<>(MetricSummaryModel.class, param.getOffset(), param.getLimit()))));
	}
	@Override
	public MetricSummaryForm save(MetricSummaryForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}
	@Override
	public void remove(MetricSummaryForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}
	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		storage.createTable();
	}

}
