package service.metricmanagement.metricsummary.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

@Service("MetricSummaryModelPhoenixCount")
@Transactional
public class MetricSummaryModelPhoenixCount extends PhoenixDaoImpl implements Count<MetricSummaryModel> {

	@Autowired
	public MetricSummaryModelPhoenixCount(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long count(Param<MetricSummaryModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}
	

}
