package service.metricmanagement.metrics.timeonpage.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.metrics.timeonpage.model.TimeOnPageMetricModel;

@Service("TimeOnPageModelPhoenixStorage")
@Transactional	
public class TimeOnPageModelPhoenixStorage  extends PhoenixDaoImpl implements Storage<TimeOnPageMetricModel>{

	@Autowired
	public TimeOnPageModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TimeOnPageMetricModel> get(Param<TimeOnPageMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public TimeOnPageMetricModel save(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void remove(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(TimeOnPageMetricModel.class);
	}

}
