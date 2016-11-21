package service.keymanagement.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.keymanagement.form.KeyForm;
import service.keymanagement.model.KeyModel;

@Service("KeyFormStorage")
@Transactional
public class KeyFormStorage implements Storage<KeyForm> {
	
	@Resource(name="${KeyFormStorage.storage}")
	private Storage<KeyModel> storage;
	@Resource(name="${KeyFormStorage.mapper}")
	private Mapper<KeyModel, KeyForm> mapper;
	
	public KeyFormStorage() {
	}

	@Override
	public List<KeyForm> get(Param<KeyForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<>(KeyModel.class))));
	}

	@Override
	public KeyForm save(KeyForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}

	@Override
	public void remove(KeyForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		storage.createTable();
	}




}
