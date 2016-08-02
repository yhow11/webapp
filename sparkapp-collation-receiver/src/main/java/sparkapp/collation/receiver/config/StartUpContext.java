package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.VisitorLogService;

@Configuration
public class StartUpContext implements ApplicationListener<ApplicationEvent> {

	@Autowired
	private VisitorLogService visitorLogService;

	public void onApplicationEvent(ApplicationEvent arg0) {
		try {
			visitorLogService.creatTable(VisitorLogModel.class);
			visitorLogService.creatTable(AnonymousVisitorModel.class);
			visitorLogService.creatTable(BrowserFPModel.class);
			visitorLogService.creatTable(DeviceFPModel.class);
			visitorLogService.creatTable(SessionModel.class);
			visitorLogService.creatTable(WebEventModel.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};
}
