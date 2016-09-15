package com.nurtureretargeting.admin.urlmanagement.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.httpclient.util.URIUtil;

import com.google.common.base.Strings;
import com.nurtureretargeting.admin.keymanagement.object.KeyForm;
import com.nurtureretargeting.admin.keymanagement.object.ValueForm;
import com.nurtureretargeting.admin.urlmanagement.object.URLForm;

import common.form.ResponsePaginationForm;
import service.urlmanagement.URLImportService;
import service.urlmanagement.URLTaggedService;
import service.urlmanagement.model.URLImportModel;
import service.urlmanagement.model.URLTaggedModel;

public class URLTaggingManager {

	private URLImportService URLImportService;
	private URLTaggedService URLTaggedService;
	
	public URLTaggingManager(URLImportService URLImportService, URLTaggedService URLTaggedService) {
		// TODO Auto-generated constructor stub
		this.URLImportService = URLImportService;
		this.URLTaggedService = URLTaggedService;
	}
	public ResponsePaginationForm<URLForm> getAll(String url, String page, String limit) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLForm> response = new ResponsePaginationForm<>();
		response.setCount(URLImportService.getCount("%" + url + "%"));
		Long limitNum = (Long.valueOf(limit) + 1);
		Long end = limitNum * Long.valueOf(page);
		Long start = (end - limitNum);
		List<URLImportModel> urlImportModels = URLImportService.getAll("%" + url + "%", start, limitNum);
		List<URLForm> urls = new ArrayList<>();
		for (URLImportModel urlImportModel : urlImportModels) {
			URLForm form = new URLForm();
			form.setPath(URIUtil.getFromPath(urlImportModel.getUrl()));
			form.setUrl(urlImportModel.getUrl());
			List<URLTaggedModel> urlModels = URLTaggedService.getAll("%" + urlImportModel.getUrl());
			for (URLTaggedModel urlModel : urlModels) {
				KeyForm keyForm = new KeyForm();
				keyForm.setKey(urlModel.gettKey());
				if (!Strings.isNullOrEmpty(urlModel.gettValues())) {
					List<ValueForm> valueForms = new ArrayList<>();
					for (String value : urlModel.gettValues().split(",")) {
						ValueForm valueForm = new ValueForm();
						valueForm.setId(UUID.randomUUID().toString());
						valueForm.setKey(urlModel.gettKey());
						valueForm.setValue(value);
						valueForms.add(valueForm);
					}
					keyForm.setValues(valueForms);
				}
				form.getKeys().add(keyForm);
			}

			urls.add(form);
		}
		response.getData().addAll(urls);

		return response;
	}
	public ResponsePaginationForm<URLForm> save(List<URLForm> urlForms) throws Exception {
		// TODO Auto-generated method stub
		ResponsePaginationForm<URLForm> response = new ResponsePaginationForm<>();
		for (URLForm urlForm : urlForms) {
			URLTaggedService.delete("%" + urlForm.getUrl());
			for (KeyForm keyForm : urlForm.getKeys()) {
				URLTaggedModel urlModel = new URLTaggedModel();
				urlModel.setUrl(urlForm.getUrl());
				urlModel.settKey(keyForm.getKey());
				if (keyForm.getValues().size() > 0) {
					List<String> values = new ArrayList<String>();
					for (ValueForm valueForm : keyForm.getValues()) {
						values.add(valueForm.getValue());
					}
					urlModel.settValues(String.join(",", values));
				}
				URLTaggedService.save(urlModel);
			}
		}

		return response;
	}
	
}
