package service.urlmanagement.urlimport.mapper;

import org.springframework.stereotype.Service;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import service.urlmanagement.urlimport.form.URLImportForm;
import service.urlmanagement.urlimport.model.URLImportModel;

@Service("URLImportMapper")
public class URLImportMapper extends Mapper<URLImportModel, URLImportForm> {

	public URLImportMapper() {
		super(URLImportModel.class, URLImportForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public URLImportModel marshall(URLImportForm source, URLImportModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		return target;
	}

	@Override
	public URLImportForm unmarshall(URLImportModel source, URLImportForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		return target;
	}
	
}
