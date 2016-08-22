//package com.fingerprint.dao.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.fingerprint.config.AppContext;
//import com.fingerprint.config.ConfigContext;
//import com.fingerprint.config.DaoContext;
//import com.fingerprint.config.MapperContext;
//import com.fingerprint.config.PhoenixContext;
//
//import helper.phoenix.dao.impl.PhoenixDaoImpl;
//import usertracker.browser.model.WebEventModel;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppContext.class, MapperContext.class,
//		ConfigContext.class, DaoContext.class, PhoenixContext.class })
//public class PhoenixDaoTest {
//
//	@Autowired
//	private PhoenixDaoImpl phoenixDaoImpl;
//	
//	@Test
//	public void testCount() throws Exception{
//		WebEventModel model = new WebEventModel();
//		model.setUrl("google.com");
//		phoenixDaoImpl.count(model);
//	}
//	
//}
