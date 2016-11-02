//package com.inctool.test.service;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import inc.member.model.MemberModel;
//import inc.member.service.MemberService;
//import test.inctool.config.AppContext;
//import test.inctool.config.MongoContext;
//import test.inctool.config.ServiceContext;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppContext.class, MongoContext.class, ServiceContext.class })
//public class INCMemberServiceTest {
//
//	@Autowired
//	private MemberService incmemberservice;
//	
//	@Test
//	public void test() throws Exception {
//		MemberModel model = new MemberModel();
//		model.setId("57ca8b60928e101852e40bbd");
//		model.setArea("123");
//		incmemberservice.save(model);
////		List<INCMemberModel> result = incmemberservice.getAll(model);
////		System.out.print(result.size());
//	}
//}
