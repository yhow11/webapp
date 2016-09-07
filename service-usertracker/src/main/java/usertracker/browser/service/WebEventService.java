package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import usertracker.browser.model.WebEventModel;

@Transactional
public interface WebEventService extends JService<WebEventModel, WebEventModel> {
	public List<WebEventModel> getAll(String avID, Long offset, Long limit) throws Exception;
}
