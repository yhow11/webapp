package usertracker.browser.service;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import usertracker.browser.model.AnonymousVisitorModel;

@Transactional
public interface AnonymousVisitorService  extends JService<AnonymousVisitorModel, AnonymousVisitorModel> {
	
	
}
