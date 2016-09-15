package service.metricmanagement.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.MetricService;
import service.metricmanagement.model.MetricModel;

public class MetricPhoenixServiceImpl extends PhoenixDaoImpl implements MetricService {

	public MetricPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	public List<MetricModel> getAll(QueryParam<MetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	public Long getCount(QueryParam<MetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	public MetricModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricModel> param = new QueryParam<MetricModel>(MetricModel.class);
		param.getModel().setId(Long.valueOf(id));
		return super.searchOne(param);
	}

	public MetricModel save(MetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	public void remove(MetricModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	public List<MetricModel> getAll(Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricModel> param = new QueryParam<MetricModel>(MetricModel.class);
		param.setLimit(limit);
		param.setOffset(offset);
		return super.search(param);
	}

	public List<MetricModel> getAll(String key) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricModel> param = new QueryParam<MetricModel>(MetricModel.class);
		param.getModel().settKey(key);
		return super.search(param);
	}

}
