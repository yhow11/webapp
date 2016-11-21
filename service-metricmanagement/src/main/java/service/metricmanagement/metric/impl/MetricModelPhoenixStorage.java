package service.metricmanagement.metric.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.metric.model.MetricModel;

@Service("MetricModelPhoenixStorage")
@Transactional
public class MetricModelPhoenixStorage extends PhoenixDaoImpl implements Storage<MetricModel> {

	@Autowired
	public MetricModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<MetricModel> get(Param<MetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}
	@Override
	public MetricModel save(MetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}
	@Override
	public void remove(MetricModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}
	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(MetricModel.class);
	}

}
