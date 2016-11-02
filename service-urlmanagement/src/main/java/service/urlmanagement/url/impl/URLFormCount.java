package service.urlmanagement.url.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.urlmanagement.url.form.URLForm;
import service.urlmanagement.urlimport.form.URLImportForm;

@Service("URLFormCount")
@Transactional
public class URLFormCount implements Count<URLForm> {

	@Resource(name="${URLFormCount.aggregator}")
	private Count<URLImportForm> aggregator;
	
	public URLFormCount() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Long count(Param<URLForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(new DefaultParam<URLImportForm>(URLImportForm.class));
	}

}
