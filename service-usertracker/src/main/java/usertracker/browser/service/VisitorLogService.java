package usertracker.browser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import usertracker.browser.model.VisitorLogModel;

@Transactional
public interface VisitorLogService extends JService<VisitorLogModel, VisitorLogModel> {
	public List<VisitorLogModel> getAll(Long offset, Long limit) throws Exception;
}
