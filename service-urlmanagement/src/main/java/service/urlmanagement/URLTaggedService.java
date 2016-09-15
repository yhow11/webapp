package service.urlmanagement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import service.urlmanagement.model.URLTaggedModel;

@Transactional
public interface URLTaggedService extends JService<URLTaggedModel, URLTaggedModel> {
	public List<URLTaggedModel> getAll(String url) throws Exception;
	public void delete(String url) throws Exception;
}
