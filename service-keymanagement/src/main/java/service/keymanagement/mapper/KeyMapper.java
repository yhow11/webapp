package service.keymanagement.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import service.keymanagement.form.KeyForm;
import service.keymanagement.form.ValueForm;
import service.keymanagement.model.KeyModel;

@Service("KeyMapper")
public class KeyMapper extends Mapper<KeyModel, KeyForm> {

	public KeyMapper() {
		super(KeyModel.class, KeyForm.class);
		// TODO Auto-generated constructor stub
	}
	@Override
	public KeyForm unmarshall(KeyModel e, KeyForm t) throws Exception {
		// TODO Auto-generated method stub
		KeyForm form = t;
		form.setKey(e.gettKey());
		ObjectUtil.isPresent(e.gettValues(), values ->{
			for(String value: values.split(",")){
				ValueForm valueForm = new ValueForm();
				valueForm.setId(UUID.randomUUID().toString());
				valueForm.setKey(e.gettKey());
				valueForm.setValue(value);
				form.getValues().add(valueForm);
			}
		});
		return form;
	}
	@Override
	public KeyModel marshall(KeyForm t, KeyModel e) throws Exception {
		// TODO Auto-generated method stub
		KeyModel model = e;
		model.settKey(t.getKey());
		List<String> values = new ArrayList<String>();
		for(ValueForm valueForm : t.getValues()){
			values.add(valueForm.getValue());
		}
		ObjectUtil.isPresent(values, items ->{
			model.settValues(String.join(",", items));
		});
		return model;
	}

}
