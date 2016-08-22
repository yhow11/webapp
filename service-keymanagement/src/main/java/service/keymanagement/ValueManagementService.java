package service.keymanagement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import service.keymanagement.model.ValueModel;

@Transactional
public interface ValueManagementService {

	public void createTable() throws Exception;
	public ValueModel save(ValueModel valueModel) throws Exception;
	public List<ValueModel> getAll(String key) throws Exception;
	public ValueModel get(Long id) throws Exception;
	public void delete(Long id) throws Exception;
}
