package service.metricmanagement.metricsummary.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

@Service("MetricSummaryModelPhoenixStorage")
@Transactional
public class MetricSummaryModelPhoenixStorage extends PhoenixDaoImpl implements Storage<MetricSummaryModel> {

	@Autowired
	public MetricSummaryModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<MetricSummaryModel> get(Param<MetricSummaryModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}
	@Override
	public MetricSummaryModel save(MetricSummaryModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}
	@Override
	public void remove(MetricSummaryModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}
	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(MetricSummaryModel.class);
	}

}
