package service.urlmanagement.urlimport.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.mapper.Mapper;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.urlmanagement.urlimport.form.URLImportForm;
import service.urlmanagement.urlimport.model.URLImportModel;

@Service("URLImportFormCount")
@Transactional
public class URLImportFormCount implements Count<URLImportForm>{

	@Resource(name="${URLImportFormCount.aggregator}")
	private Count<URLImportModel> aggregator;
	@Resource(name="${URLImportFormCount.mapper}")
	private Mapper<URLImportModel, URLImportForm> mapper;
	
	public URLImportFormCount() {
	
	}

	@Override
	public Long count(Param<URLImportForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(mapper.marshall(param, new DefaultParam<URLImportModel>(URLImportModel.class)));
	}

}
