package com.inctool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.inctool.chart.service.ChartService;
import com.inctool.chart.service.impl.ChartServiceImpl;
import com.inctool.management.service.MemberService;
import com.inctool.management.service.impl.MemberServiceImpl;
import com.inctool.management.service.impl.UserDetailsServiceImpl;
import com.jofel.service.authentication.JAuthenticationService;
import com.jofel.service.authentication.impl.JAuthenticationServiceImpl;
import com.jofel.service.management.JUserService;
import com.jofel.service.management.impl.JUserServiceImpl;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class ServiceContext {
	
	@Autowired
	private DaoContext context;
	@Autowired
	private MapperContext mapperContext;
	
	@Bean
	public JUserService userService(){
		return new JUserServiceImpl(context.userDao(), mapperContext.userMapper());
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userService());
    }
	
	@Bean
	public JAuthenticationService authenticationService(){
		return new JAuthenticationServiceImpl(userService());
	}
	
	@Bean
	public MemberService memberService() throws Exception{
		return new MemberServiceImpl(context.memberDao(), mapperContext.memberMapper());
	}
	
	@Bean
	public ChartService chartService() throws Exception{
		return new ChartServiceImpl(context.chartDao(), mapperContext.chartMapper());
	}
	
}
