package service.urlmanagement.urltagged.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.urlmanagement.urltagged.form.URLTaggedForm;
import service.urlmanagement.urltagged.model.URLTaggedModel;

@Service("URLTaggedFormStorage")
@Transactional
public class URLTaggedFormStorage implements Storage<URLTaggedForm> {

	@Resource(name="${URLTaggedFormStorage.storage}")
	private Storage<URLTaggedModel> storage;
	@Resource(name="${URLTaggedFormStorage.mapper}")
	private Mapper<URLTaggedModel, URLTaggedForm> mapper;
	
	public URLTaggedFormStorage() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<URLTaggedForm> get(Param<URLTaggedForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<URLTaggedModel>(URLTaggedModel.class))));
	}
	@Override
	public URLTaggedForm save(URLTaggedForm model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.save(mapper.marshall(model)));
	}
	@Override
	public void remove(URLTaggedForm model) throws Exception {
		// TODO Auto-generated method stub
		storage.remove(mapper.marshall(model));
	}
	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		storage.create();
	}

}
