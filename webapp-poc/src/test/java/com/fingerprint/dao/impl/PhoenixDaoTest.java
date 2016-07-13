//package com.fingerprint.dao.impl;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
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
//import usertracker.browser.dao.VisitorLogDao;
//import usertracker.browser.model.AnonymousVisitorModel;
//import usertracker.browser.model.BrowserFPModel;
//import usertracker.browser.model.DeviceFPModel;
//import usertracker.browser.model.SessionModel;
//import usertracker.browser.model.VisitorLogModel;
//import usertracker.browser.model.WebEventModel;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppContext.class, MapperContext.class,
//		ConfigContext.class, DaoContext.class, PhoenixContext.class })
//public class PhoenixDaoTest {
//
//	@Autowired
//	VisitorLogDao visitorLogDao;
//	
//
//	@Test
//	public void createTableTest() throws Exception {
//		visitorLogDao.creatTable(AnonymousVisitorModel.class);
//		visitorLogDao.creatTable(WebEventModel.class);
//		visitorLogDao.creatTable(BrowserFPModel.class);
//		visitorLogDao.creatTable(DeviceFPModel.class);
//		visitorLogDao.creatTable(SessionModel.class);
//		visitorLogDao.creatTable(VisitorLogModel.class);
//	}
//	
//
//	@Test
//	public void findByID() throws Exception {
//		WebEventModel result = visitorLogDao.getOne(WebEventModel.class, "WEBEVENT000");
//		System.out.println(result.getId());
//	}
//	
//	@Test
//	public void findByColumnTest() throws Exception {
//		List<WebEventModel> results = visitorLogDao.find(WebEventModel.class, "type", "clicked");
//		for(WebEventModel event: results) {
//			System.out.println(event.getId());
//		}
//	}
//	
//	@Test
//	public void fillTableTest() throws Exception {
//		
//			VisitorLogModel visitorLogModel = new VisitorLogModel();
//			visitorLogModel.setDeviceFP("c1754dfa96adff49a2ee9431492f2c24");
//			visitorLogModel.setId(new Random().toString());
//			visitorLogModel.setLeadID("123213");
//			visitorLogModel.setWebFP("58ef412cd7925d76743c14b273f8ec7d");
//			visitorLogModel.setSessionID("f88999f6-cafc-49fa-9a09-be744ac7d8e3");
//			visitorLogModel.setTimeStamp(new Date().getTime());
//			visitorLogModel.setTitle("google.com");
//			visitorLogModel.setType("clicked");
//			visitorLogModel.setUrl("http://google.com");
//			visitorLogDao.save(VisitorLogModel.class, visitorLogModel);
//			
//			AnonymousVisitorModel anonymousVisitorModel = new AnonymousVisitorModel();
//			anonymousVisitorModel.setId(visitorLogModel.getSessionID()+"AVAVAV");
//			visitorLogDao.save(AnonymousVisitorModel.class, anonymousVisitorModel);
//			
//			
//			
//			SessionModel sessionModel = new SessionModel();
//			sessionModel.setAnonymousVisitorID(anonymousVisitorModel.getId());
//			sessionModel.setId(visitorLogModel.getSessionID());
//			visitorLogDao.save(SessionModel.class, sessionModel);
//			
//			
//			BrowserFPModel browserFPModel = new BrowserFPModel();
//			browserFPModel.setAnonymousVisitorID(anonymousVisitorModel.getId());
//			browserFPModel.setId(visitorLogModel.getWebFP());
//			visitorLogDao.save(BrowserFPModel.class, browserFPModel);
//			
//			
//			DeviceFPModel deviceFPModel = new DeviceFPModel();
//			deviceFPModel.setAnonymousVisitorID(anonymousVisitorModel.getId());
//			deviceFPModel.setId(visitorLogModel.getDeviceFP());
//			visitorLogDao.save(DeviceFPModel.class, deviceFPModel);
//		for(int x = 0; x < 20 ; x++) {	
//			WebEventModel webEventModel = new WebEventModel();
//			webEventModel.setAnonymousVisitorID(anonymousVisitorModel.getId());
//			webEventModel.setBrowserFPID(browserFPModel.getId());
//			webEventModel.setDeviceFPID(deviceFPModel.getId());
//			webEventModel.setId(anonymousVisitorModel.getId()+x);
//			webEventModel.setTimeStamp(visitorLogModel.getTimeStamp());
//			webEventModel.setTitle(visitorLogModel.getTitle());
//			webEventModel.setType(visitorLogModel.getType());
//			webEventModel.setUrl(visitorLogModel.getUrl());
//			visitorLogDao.save(WebEventModel.class, webEventModel);
//		}
//	
//	}
//}
