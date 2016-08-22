package service.urlmanagement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import service.urlmanagement.model.URLModel;

@Transactional
public interface URLManagementService {

	public URLModel save(URLModel model) throws Exception;
	public List<URLModel> getAll(String url) throws Exception;
	public void deleteByURL(String url) throws Exception;
}
