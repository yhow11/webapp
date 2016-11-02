package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import sparkapp.collation.receiver.impl.SimplePhoenixDaoImpl;
import usertracker.browser.visitor.model.ActiveVisitorModel;

@Configuration
@Transactional
public class StartUpConfig implements ApplicationListener<ApplicationEvent> {

	@Autowired
	private SimplePhoenixDaoImpl SimplePhoenixDaoImpl;
	

	public void onApplicationEvent(ApplicationEvent arg0) {
		
//		Logger.getLogger("sparkapp.collation.receiver.Main").setLevel(Level.INFO);
//		Logger.getLogger("org.apache.spark.streaming.dstream.DStream").setLevel(Level.INFO);
//		Logger.getLogger("org.apache.spark.streaming.dstream.WindowedDStream").setLevel(Level.INFO);
//		Logger.getLogger("org.apache.spark.streaming.DStreamGraph").setLevel(Level.INFO);
//		Logger.getLogger("org.apache.spark.streaming.scheduler.JobGenerator").setLevel(Level.INFO);
//		
		try {
			SimplePhoenixDaoImpl.createTable(ActiveVisitorModel.class);
//			phoenixDaoImpl.createTable(KeySumMetricModel.class);
//			phoenixDaoImpl.createTable(TimeOnPageMetricModel.class);
//			phoenixDaoImpl.createTable(MetricSummaryModel.class);
//			phoenixDaoImpl.createTable(PageCountModel.class);
//			phoenixDaoImpl.createTable(VisitorLogModel.class);
//			phoenixDaoImpl.createTable(AnonymousVisitorModel.class);
//			phoenixDaoImpl.createTable(BrowserFPModel.class);
//			phoenixDaoImpl.createTable(DeviceFPModel.class);
//			phoenixDaoImpl.createTable(SessionModel.class);
//			phoenixDaoImpl.createTable(WebEventModel.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};
}
