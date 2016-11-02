package service.urlmanagement.urlimport.mapper;

import org.springframework.stereotype.Service;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import service.urlmanagement.urlimport.form.URLImportForm;
import service.urlmanagement.urlsitemap.form.URLSiteMapForm;

@Service("URLImportSiteMapMapper")
public class URLImportSiteMapMapper extends Mapper<URLSiteMapForm, URLImportForm> {

	public URLImportSiteMapMapper() {
		super(URLSiteMapForm.class, URLImportForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public URLSiteMapForm marshall(URLImportForm source, URLSiteMapForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		return target;
	}

	@Override
	public URLImportForm unmarshall(URLSiteMapForm source, URLImportForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		return target;
	}
	
}
