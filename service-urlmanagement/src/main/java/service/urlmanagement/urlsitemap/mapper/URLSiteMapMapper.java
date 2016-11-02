package service.urlmanagement.urlsitemap.mapper;

import org.springframework.stereotype.Service;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import service.urlmanagement.urlsitemap.form.URLSiteMapForm;
import service.urlmanagement.urlsitemap.model.URLSiteMapModel;

@Service("URLSiteMapMapper")
public class URLSiteMapMapper extends Mapper<URLSiteMapModel, URLSiteMapForm>{

	public URLSiteMapMapper() {
		super(URLSiteMapModel.class, URLSiteMapForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public URLSiteMapModel marshall(URLSiteMapForm source, URLSiteMapModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		return target;
	}

	@Override
	public URLSiteMapForm unmarshall(URLSiteMapModel source, URLSiteMapForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		return target;
	}

}
