package service.urlmanagement.impl;

import java.util.ArrayList;
import java.util.List;

import helper.jaxb.dao.impl.JAXBDao;
import helper.jaxb.generated.TUrl;
import helper.jaxb.generated.Urlset;
import service.urlmanagement.URLSiteMapService;
import service.urlmanagement.model.URLImportModel;
import service.urlmanagement.model.URLSiteMapModel;

public class URLSiteMapJAXBServiceImpl implements URLSiteMapService  {

	private JAXBDao JAXBDao;
	
	public URLSiteMapJAXBServiceImpl(JAXBDao JAXBDao) {
		// TODO Auto-generated constructor stub
		this.JAXBDao = JAXBDao;
	}


	@Override
	public List<URLSiteMapModel> getAll(String url) throws Exception {
		// TODO Auto-generated method stub
		List<URLSiteMapModel> urlModels = new ArrayList<>();
		
		Urlset urlset = JAXBDao.get(Urlset.class, url);
		for(TUrl tUrl: urlset.getUrl()){
			URLSiteMapModel urlModel = new URLSiteMapModel();
			urlModel.setUrl(tUrl.getLoc());
			urlModels.add(urlModel);
		}
		return urlModels;
	}

}
