package service.urlmanagement.url.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.httpclient.util.URIUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.ObjectUtil;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.keymanagement.form.KeyForm;
import service.keymanagement.form.ValueForm;
import service.urlmanagement.url.form.URLForm;
import service.urlmanagement.urlimport.form.URLImportForm;
import service.urlmanagement.urltagged.form.URLTaggedForm;

@Service("URLFormStorage")
@Transactional
public class URLFormStorage implements Storage<URLForm> {

	@Resource(name="${URLFormStorage.importFormStorage}")
	private Storage<URLImportForm> urlImportFormStorage;
	@Resource(name="${URLFormStorage.urlTaggedFormStorage}")
	private Storage<URLTaggedForm> urlTaggedFormStorage;
	
	public URLFormStorage() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<URLForm> get(Param<URLForm> param) throws Exception {
		// TODO Auto-generated method stub
		Param<URLImportForm> importParam = new DefaultParam<URLImportForm>(URLImportForm.class, param.getOffset(), param.getLimit());
		importParam.getModel().setUrl("%" + param.getModel().getUrl() + "%");
		List<URLImportForm> urlImportForms = urlImportFormStorage.get(importParam);
		List<URLForm> urls = new ArrayList<>();
		for (URLImportForm urlImportForm : urlImportForms) {
			URLForm form = new URLForm();
			form.setPath(URIUtil.getFromPath(urlImportForm.getUrl()));
			form.setUrl(urlImportForm.getUrl());
			Param<URLTaggedForm> taggedParam = new DefaultParam<URLTaggedForm>(URLTaggedForm.class);
			taggedParam.getModel().setUrl("%" + urlImportForm.getUrl());
			List<URLTaggedForm> urlTaggedForms = urlTaggedFormStorage.get(taggedParam);
			for (URLTaggedForm urlTaggedForm : urlTaggedForms) {
				KeyForm keyForm = new KeyForm();
				keyForm.setKey(urlTaggedForm.getKey());
				ObjectUtil.isPresent(urlTaggedForm.getValues(), values ->{
					List<ValueForm> valueForms = new ArrayList<>();
					for (String value : values.split(",")) {
						ValueForm valueForm = new ValueForm();
						valueForm.setId(UUID.randomUUID().toString());
						valueForm.setKey(urlTaggedForm.getKey());
						valueForm.setValue(value);
						valueForms.add(valueForm);
					}
					keyForm.setValues(valueForms);
				});
				form.getKeys().add(keyForm);
			}

			urls.add(form);
		}
		return urls;
	}
	@Override
	public URLForm save(URLForm form) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			remove(form);
			
			for (KeyForm keyForm : form.getKeys()) {
				URLTaggedForm urlTaggedForm = new URLTaggedForm();
				urlTaggedForm.setUrl(form.getUrl());
				urlTaggedForm.setKey(keyForm.getKey());
				if (keyForm.getValues().size() > 0) {
					List<String> values = new ArrayList<String>();
					for (ValueForm valueForm : keyForm.getValues()) {
						values.add(valueForm.getValue());
					}
					urlTaggedForm.setValues(String.join(",", values));
				}
				urlTaggedFormStorage.save(urlTaggedForm);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void remove(URLForm model) throws Exception {
		// TODO Auto-generated method stub
		Param<URLTaggedForm> param = new DefaultParam<>(URLTaggedForm.class);
		param.getModel().setUrl("%" +model.getUrl());
		urlTaggedFormStorage.get(param).stream().forEach(tagged -> {
			try {
				urlTaggedFormStorage.remove(tagged);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
