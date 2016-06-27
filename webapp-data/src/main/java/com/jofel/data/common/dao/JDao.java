package com.jofel.data.common.dao;

import java.util.List;

import com.jofel.data.common.dao.param.JDaoParam;

public interface JDao {
	
	public <T> List<T> find(JDaoParam param, Class<T> clazz);
	public <T> T findOne(JDaoParam param, Class<T> clazz);
	public <T> T save(JDaoParam param, Class<T> clazz);
}
