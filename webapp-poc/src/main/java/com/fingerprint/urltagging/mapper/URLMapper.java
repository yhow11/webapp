/*package com.fingerprint.urltagging.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.phoenix.shaded.org.apache.directory.api.util.Strings;

import com.fingerprint.keymanagement.object.KeyForm;
import com.fingerprint.keymanagement.object.ValueForm;
import com.fingerprint.mapper.Mapper;
import com.fingerprint.urltagging.object.URLForm;
import com.fingerprint.urltagging.object.URLFormUnWind;

public class URLMapper  implements Mapper<URLFormUnWind, URLForm> {


	@Override
	public URLFormUnWind unmarshall(URLForm e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URLForm marshall(URLFormUnWind t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<URLFormUnWind> unmarshall(List<URLForm> e) {
		// TODO Auto-generated method stub
		List<URLFormUnWind> URLFormUnWinds = new ArrayList<>();
		for(URLForm form: e){
			for(KeyForm keyForm: form.getKeys()){
				URLFormUnWind URLFormUnWind = new URLFormUnWind();
				URLFormUnWind.setPath(form.getPath());
				URLFormUnWind.setKeyID(keyForm.getId());
				List<String> valueIDs = new ArrayList<>();
				for(ValueForm valueForm: keyForm.getNewValues()){
					valueIDs.add(valueForm.getId());
				}
				URLFormUnWind.setValueIDs(String.join(",", valueIDs));
				URLFormUnWinds.add(URLFormUnWind);
			}
		}
		
		return URLFormUnWinds;
	}

	@Override
	public List<URLForm> marshall(List<URLFormUnWind> t) {
		// TODO Auto-generated method stub
		List<URLForm> URLForms = new ArrayList<>();
		Map<String, List<KeyForm>> keyMap = new HashMap<>();
		for(URLFormUnWind form: t){
			keyMap.put(form.getPath(), new ArrayList<>());
		}
		for(URLFormUnWind form: t){
			KeyForm keyForm = new KeyForm();
			keyForm.setId(form.getKeyID());
			if(!Strings.isNotEmpty(form.getValueIDs())){
				for(String valueID: form.getValueIDs().split(",")){
					ValueForm valueForm = new ValueForm();
					valueForm.setId(valueID);
					keyForm.getNewValues().add(valueForm);
				}
			}
			keyMap.get(form.getPath()).add(keyForm);
		}
		for(String url: keyMap.keySet()){
			URLForm urlForm = new URLForm();
			urlForm.setPath(url);
			urlForm.setKeys(keyMap.get(url));
			URLForms.add(urlForm);
		}
		return URLForms;
	}


}
*/