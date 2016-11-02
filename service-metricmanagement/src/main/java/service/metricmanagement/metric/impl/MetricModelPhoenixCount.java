package service.metricmanagement.metric.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.metric.model.MetricModel;

@Service("MetricModelPhoenixCount")
@Transactional
public class MetricModelPhoenixCount extends PhoenixDaoImpl implements Count<MetricModel> {

	@Autowired
	public MetricModelPhoenixCount(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long count(Param<MetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}


}
