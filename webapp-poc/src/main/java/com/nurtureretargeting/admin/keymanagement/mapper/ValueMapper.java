package com.nurtureretargeting.admin.keymanagement.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.nurtureretargeting.admin.keymanagement.object.ValueForm;

import common.mapper.OneToManyMapper;
import joptsimple.internal.Strings;
import service.keymanagement.model.KeyModel;

public class ValueMapper implements OneToManyMapper<ValueForm, KeyModel> {

	@Override
	public List<ValueForm> unmarshall(KeyModel e) {
		// TODO Auto-generated method stub
		return unmarshall(e, new ArrayList<ValueForm>());
	}

	@Override
	public KeyModel marshall(List<ValueForm> t) {
		// TODO Auto-generated method stub
		return marshall(t, new KeyModel());
	}

	@Override
	public List<ValueForm> unmarshall(KeyModel e, List<ValueForm> t) {
		// TODO Auto-generated method stub
		if(!Strings.isNullOrEmpty(e.gettValues())){
			for(String value: e.gettValues().split(",")){
				ValueForm valueForm = new ValueForm();
				valueForm.setId(UUID.randomUUID().toString());
				valueForm.setKey(e.gettKey());
				valueForm.setValue(value);
				t.add(valueForm);
			}
		}
		return t;
	}

	@Override
	public KeyModel marshall(List<ValueForm> t, KeyModel e) {
		// TODO Auto-generated method stub
		List<String> values = new ArrayList<String>();
		for(ValueForm valueForm : t){
			values.add(valueForm.getValue());
		}
		if(values.size() > 0){
			e.settValues(String.join(",", values));
		}
		return e;
	}

}
