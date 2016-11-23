package service.segment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.segment.form.SegmentedVisitorForm;
import service.segment.model.SegmentedVisitorModel;

@Service("SegmentedVisitorFormStorage")
@Transactional
public class SegmentedVisitorFormStorage implements Storage<SegmentedVisitorForm> {

	@Resource(name="${SegmentedVisitorFormStorage.storage}")
	private Storage<SegmentedVisitorModel> storage;
	@Resource(name="${SegmentedVisitorFormStorage.mapper}")
	private Mapper<SegmentedVisitorModel, SegmentedVisitorForm> mapper;
	
	public SegmentedVisitorFormStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SegmentedVisitorForm> get(Param<SegmentedVisitorForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<>(SegmentedVisitorModel.class))));
	}

	@Override
	public void remove(SegmentedVisitorForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}

	@Override
	public SegmentedVisitorForm save(SegmentedVisitorForm model) throws Exception {
		// TODO Auto-generated method stub
		return  mapper.unmarshall(storage.save(mapper.marshall(model)));
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		storage.createTable();
	}

}
