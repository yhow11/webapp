package com.inctool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import inc.member.service.MemberCommandService;
import inc.member.service.MemberQueryService;
import inc.member.service.MemberService;
import inc.member.service.WorkerCommandService;
import inc.member.service.WorkerQueryService;
import inc.member.service.WorkerService;
import inc.member.service.impl.MemberModelSaveCommand;
import inc.member.service.impl.MemberFormQueryOneByKey;
import inc.member.service.impl.MemberQueryMongoServiceImpl;
import inc.member.service.impl.MemberFormQueryOneAdapter;
import inc.member.service.impl.MemberServiceImpl;
import inc.member.service.impl.WorkerCommandMongoServiceImpl;
import inc.member.service.impl.WorkerQueryMongoServiceImpl;
import inc.member.service.impl.WorkerServiceImpl;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class ServiceContext {
	
	@Autowired
	private MongoContext mongoContext;
	
	@Autowired
	private MapperContext mapperContext;
	
	@Bean
	public MemberQueryService memberQueryMongoService() throws Exception{
		return new MemberQueryMongoServiceImpl(mongoContext.mongoTemplate(), mapperContext.memberMapper());
	}
	
	@Bean
	public MemberQueryService memberQueryTemplateService() throws Exception{
		return new MemberFormQueryOneAdapter(mongoContext.mongoTemplate(), mapperContext.memberMapper());
	}
	
	@Bean
	public MemberQueryService memberQueryAdapterService() throws Exception{
		return new MemberFormQueryOneByKey(memberQueryMongoService(), memberQueryTemplateService());
	}
	@Bean
	public MemberCommandService memberCommandService() throws Exception{
		return new MemberModelSaveCommand(mongoContext.mongoTemplate(), mapperContext.memberMapper());
	}
	@Bean
	public MemberService memberService() throws Exception{
		return new MemberServiceImpl(memberQueryAdapterService(), memberCommandService());
	}
	
	@Bean
	public WorkerQueryService workerQueryService() throws Exception{
		return new WorkerQueryMongoServiceImpl(mongoContext.mongoTemplate(), mapperContext.workerMapper());
	}
	@Bean
	public WorkerCommandService workerCommandService() throws Exception{
		return new WorkerCommandMongoServiceImpl(mongoContext.mongoTemplate(), mapperContext.workerMapper());
	}
	@Bean
	public WorkerService workerService() throws Exception{
		return new WorkerServiceImpl(workerQueryService(), workerCommandService());
	}
}
