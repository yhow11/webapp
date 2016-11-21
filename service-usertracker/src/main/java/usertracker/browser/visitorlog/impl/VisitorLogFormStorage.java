package usertracker.browser.visitorlog.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import usertracker.browser.visitorlog.form.VisitorLogForm;
import usertracker.browser.visitorlog.model.VisitorLogModel;

@Service("VisitorLogFormStorage")
@Transactional
public class VisitorLogFormStorage implements Storage<VisitorLogForm> {
	
	@Resource(name="${VisitorLogFormStorage.storage}")
	private Storage<VisitorLogModel> storage;
	@Resource(name="${VisitorLogFormStorage.mapper}")
	private Mapper<VisitorLogModel, VisitorLogForm> mapper;
	
	public VisitorLogFormStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<VisitorLogForm> get(Param<VisitorLogForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<>(VisitorLogModel.class))));
	}

	@Override
	public VisitorLogForm save(VisitorLogForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}

	@Override
	public void remove(VisitorLogForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		storage.createTable();
	}
	
	
}
