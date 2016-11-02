package service.urlmanagement.urlimport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.urlmanagement.urlimport.form.URLImportForm;
import service.urlmanagement.urlsitemap.form.URLSiteMapForm;

@Service("URLImportSiteMapStorage")
@Transactional
public class URLImportSiteMapStorage implements Storage<URLImportForm> {

	@Autowired
	@Qualifier("URLSiteMapFormStorage")
	@Resource(name="${URLImportSiteMapStorage.storage}")
	private Storage<URLSiteMapForm> storage;
	@Autowired
	@Resource(name="${URLImportSiteMapStorage.mapper}")
	private Mapper<URLSiteMapForm, URLImportForm> mapper;
	
	public URLImportSiteMapStorage() {
	
	}
	@Override
	public List<URLImportForm> get(Param<URLImportForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<URLSiteMapForm>(URLSiteMapForm.class))));
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
