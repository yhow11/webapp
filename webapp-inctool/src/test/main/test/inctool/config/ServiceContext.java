//package test.inctool.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import inc.member.service.MemberService;
//import inc.member.service.impl.MemberMongoServiceImpl;
//import test.inctool.config.MongoContext;
//
//@Configuration
//@PropertySource("classpath:com/inctool/properties/application.properties")
//public class ServiceContext {
//	
//	@Autowired
//	private MongoContext mongoContext;
//	
//	@Bean
//	public MemberService incMemberService() throws Exception{
//		return new MemberMongoServiceImpl(mongoContext.mongoTemplate());
//	}
//}
