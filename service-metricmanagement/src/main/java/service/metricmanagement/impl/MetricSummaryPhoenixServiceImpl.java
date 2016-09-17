package service.metricmanagement.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import common.query.QueryParam;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.MetricSummaryService;
import service.metricmanagement.model.MetricModel;
import service.metricmanagement.model.MetricSummaryModel;

public class MetricSummaryPhoenixServiceImpl extends PhoenixDaoImpl implements MetricSummaryService {

	public MetricSummaryPhoenixServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	public List<MetricSummaryModel> getAll(QueryParam<MetricSummaryModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	public Long getCount(QueryParam<MetricSummaryModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	public MetricSummaryModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricSummaryModel> param = new QueryParam<MetricSummaryModel>(MetricSummaryModel.class);
		param.getModel().setVISITORID(id);
		return super.searchOne(param);
	}

	public MetricSummaryModel save(MetricSummaryModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	public void remove(MetricSummaryModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	public List<MetricSummaryModel> getAll(Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricSummaryModel> param = new QueryParam<MetricSummaryModel>(MetricSummaryModel.class);
		param.setLimit(limit);
		param.setOffset(offset);
		return super.search(param);
	}

	public List<MetricModel> getAll(String key) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricModel> param = new QueryParam<MetricModel>(MetricModel.class);
		param.getModel().setTKEY(key);
		return super.search(param);
	}

}
