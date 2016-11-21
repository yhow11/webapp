package service.segment.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.segment.model.SegmentModel;

@Service("SegmentModelCount")
@Transactional
public class SegmentModelCount extends PhoenixDaoImpl implements Count<SegmentModel> {

	@Autowired
	public SegmentModelCount(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Long count(Param<SegmentModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}

}
