package service.metricmanagement.metric.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.metricmanagement.metric.form.MetricForm;
import service.metricmanagement.metric.model.MetricModel;

@Service("MetricFormStorage")
@Transactional
public class MetricFormStorage implements Storage<MetricForm> {

	@Resource(name="${MetricFormStorage.storage}")
	private Storage<MetricModel> storage;
	@Resource(name="${MetricFormStorage.mapper}")
	private Mapper<MetricModel, MetricForm> mapper;
	
	public MetricFormStorage() {
	}
	@Override
	public List<MetricForm> get(Param<MetricForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<>(MetricModel.class))));
	}
	@Override
	public MetricForm save(MetricForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}
	@Override
	public void remove(MetricForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}
	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		storage.create();
	}

}
