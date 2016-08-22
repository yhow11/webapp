package service.keymanagement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import service.keymanagement.model.KeyModel;

@Transactional
public interface KeyManagementService {

	public void createTable() throws Exception;
	public KeyModel save(KeyModel keyModel) throws Exception;
	public List<KeyModel> getAll(String key) throws Exception;
	public List<KeyModel> getAll(Long offset, Long limit) throws Exception;
	public KeyModel get(String key) throws Exception;
	public void delete(String key) throws Exception;
}
