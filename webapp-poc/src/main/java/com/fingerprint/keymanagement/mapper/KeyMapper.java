package com.fingerprint.keymanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.mapper.Mapper;

import service.keymanagement.model.KeyModel;

public class KeyMapper implements Mapper<KeyForm, KeyModel> {

	@Override
	public KeyForm unmarshall(KeyModel e) {
		// TODO Auto-generated method stub
		return unmarshall(e, new KeyForm());
	}

	@Override
	public KeyModel marshall(KeyForm t) {
		// TODO Auto-generated method stub
		return marshall(t, new KeyModel());
	}

	@Override
	public List<KeyForm> unmarshall(List<KeyModel> e) {
		// TODO Auto-generated method stub
		List<KeyForm> forms = new ArrayList<KeyForm>();
		for(KeyModel item: e){
			forms.add(unmarshall(item));
		}
		return forms;
	}

	@Override
	public List<KeyModel> marshall(List<KeyForm> t) {
		// TODO Auto-generated method stub
		List<KeyModel> models = new ArrayList<KeyModel>();
		for(KeyForm item: t){
			models.add(marshall(item));
		}
		return models;
	}
	@Override
	public KeyForm unmarshall(KeyModel e, KeyForm t) {
		// TODO Auto-generated method stub
		KeyForm form = t;
		form.setKey(e.gettKey());
		return form;
	}
	@Override
	public KeyModel marshall(KeyForm t, KeyModel e) {
		// TODO Auto-generated method stub
		KeyModel model = e;
		model.settKey(t.getKey());
		return model;
	}

}
