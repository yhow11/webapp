package test.inctool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import service.membermanagement.service.INCMemberService;
import service.membermanagement.service.impl.INCMemberMongoServiceImpl;
import test.inctool.config.MongoContext;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class ServiceContext {
	
	@Autowired
	private MongoContext mongoContext;
	
	@Bean
	public INCMemberService incMemberService() throws Exception{
		return new INCMemberMongoServiceImpl(mongoContext.mongoTemplate());
	}
}
