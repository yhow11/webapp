package service.urlmanagement.urltagged.mapper;

import org.springframework.stereotype.Service;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import service.urlmanagement.urltagged.form.URLTaggedForm;
import service.urlmanagement.urltagged.model.URLTaggedModel;

@Service("URLTaggedMapper")
public class URLTaggedMapper extends Mapper<URLTaggedModel, URLTaggedForm>{

	public URLTaggedMapper() {
		super(URLTaggedModel.class, URLTaggedForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public URLTaggedModel marshall(URLTaggedForm source, URLTaggedModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		ObjectUtil.isPresent(source.getKey(), value -> target.settKey(value));
		ObjectUtil.isPresent(source.getValues(), value -> target.settValues(value));
		return target;
	}

	@Override
	public URLTaggedForm unmarshall(URLTaggedModel source, URLTaggedForm target)throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		ObjectUtil.isPresent(source.gettKey(), value -> target.setKey(value));
		ObjectUtil.isPresent(source.gettValues(), value -> target.setValues(value));
		return target;
	}


}
