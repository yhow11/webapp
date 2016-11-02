package service.urlmanagement.urlsitemap.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.jaxb.dao.impl.JAXBDao;
import helper.jaxb.generated.TUrl;
import helper.jaxb.generated.Urlset;
import service.urlmanagement.urlsitemap.model.URLSiteMapModel;

@Service("URLSiteMapModelStorage")
@Transactional
public class URLSiteMapModelStorage implements Storage<URLSiteMapModel> {
	
	@Autowired
	private JAXBDao JAXBDao;

	public URLSiteMapModelStorage() {
	}

	@Override
	public List<URLSiteMapModel> get(Param<URLSiteMapModel> param) throws Exception {
		// TODO Auto-generated method stub
		List<URLSiteMapModel> urlModels = new ArrayList<>();
		
		Urlset urlset = JAXBDao.get(Urlset.class, param.getModel().getUrl());
		for(TUrl tUrl: urlset.getUrl()){
			URLSiteMapModel urlModel = new URLSiteMapModel();
			urlModel.setUrl(tUrl.getLoc());
			urlModels.add(urlModel);
		}
		return urlModels;
	}

	@Override
	public URLSiteMapModel save(URLSiteMapModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not supported.");
	}

	@Override
	public void remove(URLSiteMapModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not supported.");
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not supported.");
	}

}
