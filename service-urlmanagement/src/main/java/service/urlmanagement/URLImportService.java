package service.urlmanagement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import service.urlmanagement.model.URLImportModel;

@Transactional
public interface URLImportService  extends JService<URLImportModel, URLImportModel> {
	public List<URLImportModel> getAll(String url, Long offset, Long limit) throws Exception;
	public Long getCountDistinctURL(String url) throws Exception;
	public Long getCount(String url) throws Exception;
}
