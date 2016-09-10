package usertracker.browser.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VisitorRawLogService {
	public void save(String log) throws Exception;
}
