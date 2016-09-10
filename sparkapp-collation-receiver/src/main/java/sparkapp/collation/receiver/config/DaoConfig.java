package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import helper.phoenix.dao.impl.SimplePhoenixDaoImpl;

@Configuration
public class DaoConfig {
	
	@Autowired
	private PhoenixContext phoenixContext;
	
	@Bean
	public SimplePhoenixDaoImpl simplePhoenixDaoImpl() throws Exception{
		return new SimplePhoenixDaoImpl(phoenixContext.sessionFactory());
	}
	
}
