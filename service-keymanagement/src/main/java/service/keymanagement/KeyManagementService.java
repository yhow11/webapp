package service.keymanagement;

import java.util.List;

import common.service.JService;
import service.keymanagement.model.KeyModel;



public interface KeyManagementService extends JService<KeyModel, KeyModel>{

	public void createTable() throws Exception;
	public List<KeyModel> getAll(String key,  Long offset, Long limit) throws Exception;
	public List<KeyModel> getAll(String key) throws Exception;
	public void remove(String key) throws Exception;
}
