package service.segment.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.segment.model.SegmentedVisitorModel;

@Service("SegmentedVisitorModelPhoenixStorage")
@Transactional
public class SegmentedVisitorModelPhoenixStorage extends PhoenixDaoImpl implements Storage<SegmentedVisitorModel> {

	@Autowired
	public SegmentedVisitorModelPhoenixStorage(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SegmentedVisitorModel> get(Param<SegmentedVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public void remove(SegmentedVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		super.delete(model);
	}

	@Override
	public SegmentedVisitorModel save(SegmentedVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.upsert(model);
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		super.createTable(SegmentedVisitorModel.class);
	}

}
