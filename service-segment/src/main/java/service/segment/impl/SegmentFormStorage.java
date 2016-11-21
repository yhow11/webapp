package service.segment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.segment.form.SegmentForm;
import service.segment.model.SegmentModel;

@Service("SegmentFormStorage")
@Transactional
public class SegmentFormStorage implements Storage<SegmentForm> {

	@Resource(name="${SegmentFormStorage.storage}")
	private Storage<SegmentModel> storage;
	@Resource(name="${SegmentFormStorage.mapper}")
	private Mapper<SegmentModel, SegmentForm> mapper;
	
	@Override
	public List<SegmentForm> get(Param<SegmentForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<>(SegmentModel.class))));
	}

	@Override
	public void remove(SegmentForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}

	@Override
	public SegmentForm save(SegmentForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		storage.createTable();
	}


}
