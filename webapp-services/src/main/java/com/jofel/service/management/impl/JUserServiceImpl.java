package com.jofel.service.management.impl;

import com.jofel.data.common.dao.JDao;
import com.jofel.data.common.dao.param.JDaoParam;
import com.jofel.data.management.model.JUserModel;
import com.jofel.mapper.management.JUserMapper;
import com.jofel.service.common.response.JServiceResponse.ResponseType;
import com.jofel.service.management.JUserService;
import com.jofel.service.management.param.JUserParam;
import com.jofel.service.management.response.JUserResponse;

public class JUserServiceImpl implements JUserService{
	
	private JDao dao;
	private JUserMapper userMapper;
	
	public JUserServiceImpl(JDao dao, JUserMapper userMapper) {
		// TODO Auto-generated constructor stub
		this.dao = dao;
		this.userMapper = userMapper;
	}

	public JUserResponse find(JUserParam param) {
		// TODO Auto-generated method stub
		JUserResponse response = new JUserResponse();
		JDaoParam daoParam = new JDaoParam();
		response.setDataList(userMapper.map(dao.find(daoParam, JUserModel.class)));
		response.setStatus(ResponseType.SUCCESS);
		return response;
	}

	public JUserResponse findOne(JUserParam param) {
		// TODO Auto-generated method stub
		JUserResponse response = new JUserResponse();
		JDaoParam daoParam = new JDaoParam();
		response.setData(userMapper.map(dao.findOne(daoParam, JUserModel.class)));
		response.setStatus(ResponseType.SUCCESS);
		return response;
	}

}
