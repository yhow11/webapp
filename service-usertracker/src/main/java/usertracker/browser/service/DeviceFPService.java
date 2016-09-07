package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import usertracker.browser.model.DeviceFPModel;

@Transactional
public interface DeviceFPService extends JService<DeviceFPModel, DeviceFPModel> {
	public List<DeviceFPModel> getAll(String avID) throws Exception;
	public DeviceFPModel getOrCreate(String id, String av) throws Exception;
}
