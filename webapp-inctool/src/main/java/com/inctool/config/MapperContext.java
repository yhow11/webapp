package com.inctool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import inc.member.mapper.AttendanceMapper;
import inc.member.mapper.DateMapper;
import inc.member.mapper.MemberMapper;
import inc.member.mapper.OptionMapper;
import inc.member.mapper.WorkerMapper;
import inc.member.mapper.impl.AttendanceMapperImpl;
import inc.member.mapper.impl.DateMapperImpl;
import inc.member.mapper.impl.MemberMapperImpl;
import inc.member.mapper.impl.OptionMapperImpl;
import inc.member.mapper.impl.WorkerMapperImpl;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class MapperContext {
	
	
	@Bean
	public AttendanceMapper attendanceMapper(){
		return new AttendanceMapperImpl();
	}
	@Bean
	public DateMapper dateMapper(){
		return new DateMapperImpl();
	}
	@Bean
	public OptionMapper optionMapper(){
		return new OptionMapperImpl();
	}
	@Bean
	public MemberMapper memberMapper(){
		return new MemberMapperImpl(dateMapper(), optionMapper(), attendanceMapper());
	}
	@Bean
	public WorkerMapper workerMapper(){
		return new WorkerMapperImpl(optionMapper());
	}
	
}
