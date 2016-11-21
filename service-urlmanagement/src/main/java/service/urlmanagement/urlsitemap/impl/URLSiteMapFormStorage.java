package service.urlmanagement.urlsitemap.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.urlmanagement.urlsitemap.form.URLSiteMapForm;
import service.urlmanagement.urlsitemap.model.URLSiteMapModel;

@Service("URLSiteMapFormStorage")
@Transactional
public class URLSiteMapFormStorage implements Storage<URLSiteMapForm> {
	
	@Resource(name="${URLSiteMapFormStorage.storage}")
	private Storage<URLSiteMapModel> storage;
	@Resource(name="${URLSiteMapFormStorage.mapper}")
	private Mapper<URLSiteMapModel, URLSiteMapForm> mapper;

	public URLSiteMapFormStorage() {
		
	}

	@Override
	public List<URLSiteMapForm> get(Param<URLSiteMapForm> param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.unmarshall(storage.get(mapper.marshall(param, new DefaultParam<URLSiteMapModel>(URLSiteMapModel.class))));
	}

	@Override
	public URLSiteMapForm save(URLSiteMapForm model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not supported.");
	}

	@Override
	public void remove(URLSiteMapForm model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not supported.");
	}

}
