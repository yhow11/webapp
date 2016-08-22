package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import usertracker.browser.model.DeviceFPModel;

@Transactional
public interface DeviceFPService {
	
	public DeviceFPModel save(DeviceFPModel model) throws Exception;

	public void delete(String id) throws Exception;

	public DeviceFPModel get(String id) throws Exception;
	
	public DeviceFPModel getOrCreate(String id, String av) throws Exception;
	
	public List<DeviceFPModel> getAll(String avID) throws Exception;
}
