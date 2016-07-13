package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.dao.impl.VisitorLogDaoImpl;

@Configuration
public class DaoConfig {
	
	@Autowired
	private PhoenixContext phoenixContext;
	
	
	@Bean
	public VisitorLogDao visitorLogDao() throws Exception{
		return new VisitorLogDaoImpl(phoenixContext.defaultDSLContext(), phoenixContext.sessionFactory());
	}
	
}
