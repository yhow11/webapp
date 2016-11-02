package service.urlmanagement.urlimport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.urlmanagement.urlimport.form.URLImportForm;
import service.urlmanagement.urlimport.model.URLImportModel;

@Service("URLImportFormStorage")
@Transactional
public class URLImportFormStorage implements Storage<URLImportForm> {

	@Resource(name="${URLImportFormStorage.storage}")
	private Storage<URLImportModel> storage;
	@Resource(name="${URLImportFormStorage.mapper}")
	private Mapper<URLImportModel, URLImportForm> mapper;
	
	public URLImportFormStorage() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<URLImportForm> get(Param<URLImportForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<URLImportModel>(URLImportModel.class))));
	}
	@Override
	public URLImportForm save(URLImportForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}
	@Override
	public void remove(URLImportForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}
	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		storage.create();
	}

}
