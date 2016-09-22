package sparkapp.collation.receiver.config;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import service.metricmanagement.keysum.model.KeySumMetricModel;
import service.metricmanagement.model.MetricSummaryModel;
import service.metricmanagement.pagecount.model.PageCountModel;
import service.metricmanagement.timeonpage.model.TimeOnPageMetricModel;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;

@Configuration
public class StartUpContext implements ApplicationListener<ApplicationEvent> {

	@Autowired
	private PhoenixDaoImpl phoenixDaoImpl;

	public void onApplicationEvent(ApplicationEvent arg0) {
		
		Logger.getLogger("sparkapp.collation.receiver.Main").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.dstream.DStream").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.dstream.WindowedDStream").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.DStreamGraph").setLevel(Level.INFO);
		Logger.getLogger("org.apache.spark.streaming.scheduler.JobGenerator").setLevel(Level.INFO);
		
		try {
			phoenixDaoImpl.createTable(KeySumMetricModel.class);
			phoenixDaoImpl.createTable(TimeOnPageMetricModel.class);
			phoenixDaoImpl.createTable(MetricSummaryModel.class);
			phoenixDaoImpl.createTable(PageCountModel.class);
			phoenixDaoImpl.createTable(VisitorLogModel.class);
			phoenixDaoImpl.createTable(AnonymousVisitorModel.class);
			phoenixDaoImpl.createTable(BrowserFPModel.class);
			phoenixDaoImpl.createTable(DeviceFPModel.class);
			phoenixDaoImpl.createTable(SessionModel.class);
			phoenixDaoImpl.createTable(WebEventModel.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};
}
