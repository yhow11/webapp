package com.fingerprint.keymanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fingerprint.keymanagement.object.ValueForm;
import com.fingerprint.mapper.Mapper;
import com.google.common.base.Strings;

import service.keymanagement.model.ValueModel;

public class ValueMapper implements Mapper<ValueForm, ValueModel> {

	@Override
	public ValueForm unmarshall(ValueModel e, ValueForm t) {
		// TODO Auto-generated method stub
		t.setId(String.valueOf(e.getId()));
		t.setKey(e.gettKey());
		t.setValue(e.gettValue());
		return t;
	}

	@Override
	public ValueModel marshall(ValueForm t, ValueModel e) {
		// TODO Auto-generated method stub
		if(!Strings.isNullOrEmpty(t.getId())){
			e.setId(Long.valueOf(t.getId()));
		}
		e.settKey(t.getKey());
		e.settValue(t.getValue());
		return e;
	}

	@Override
	public ValueForm unmarshall(ValueModel e) {
		// TODO Auto-generated method stub
		return unmarshall(e, new ValueForm());
	}

	@Override
	public ValueModel marshall(ValueForm t) {
		// TODO Auto-generated method stub
		return marshall(t, new ValueModel());
	}

	@Override
	public List<ValueForm> unmarshall(List<ValueModel> e) {
		// TODO Auto-generated method stub
		List<ValueForm> valueForms = new ArrayList<>();
		for(ValueModel valueModel: e){
			valueForms.add(unmarshall(valueModel));
		}
		return valueForms;
	}

	@Override
	public List<ValueModel> marshall(List<ValueForm> t) {
		// TODO Auto-generated method stub
		List<ValueModel> valueModels = new ArrayList<>();
		for(ValueForm valueForm: t){
			valueModels.add(marshall(valueForm));
		}
		return valueModels;
	}

}
