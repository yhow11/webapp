package service.segment.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.segment.model.SegmentedVisitorModel;

@Service("SegmentedVisitorModelCount")
@Transactional
public class SegmentedVisitorModelCount extends PhoenixDaoImpl implements Count<SegmentedVisitorModel> {

	@Autowired
	public SegmentedVisitorModelCount(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Long count(Param<SegmentedVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}

}
