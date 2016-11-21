package usertracker.browser.visitor.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import usertracker.browser.visitor.form.ActiveVisitorForm;
import usertracker.browser.visitor.model.ActiveVisitorModel;

@Service("ActiveVisitorFormStorage")
@Transactional
public class ActiveVisitorFormStorage implements Storage<ActiveVisitorForm> {
	
	@Resource(name="${ActiveVisitorFormStorage.mapper}")
	private Mapper<ActiveVisitorModel, ActiveVisitorForm> mapper;
	
	@Resource(name="${ActiveVisitorFormStorage.storage}")
	private Storage<ActiveVisitorModel> storage;
	
	public ActiveVisitorFormStorage() throws Exception {
	}
	
	@Override
	public List<ActiveVisitorForm> get(Param<ActiveVisitorForm> param) throws Exception {
		// TODO Auto-generated method stub
		List<ActiveVisitorModel> data = storage.get(mapper.marshall(param, new DefaultParam<ActiveVisitorModel>(ActiveVisitorModel.class)));
		return mapper.unmarshall(data);
	}
	@Override
	public ActiveVisitorForm save(ActiveVisitorForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}
	@Override
	public void remove(ActiveVisitorForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}
	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		storage.createTable();
	}
	

}
