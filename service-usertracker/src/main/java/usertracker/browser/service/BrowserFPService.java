package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import usertracker.browser.model.BrowserFPModel;

@Transactional
public interface BrowserFPService  extends JService<BrowserFPModel, BrowserFPModel>  {
	public List<BrowserFPModel> getAll(String avID) throws Exception;
	public BrowserFPModel getOrCreate(String id, String av) throws Exception;
	
}
