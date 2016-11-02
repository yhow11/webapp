package com.nurtureretargeting.dao.impl;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nurtureretargeting.dao.ApplicationDao;

import common.orm.query.Storage;
import helper.phoenix.dao.impl.PhoenixDaoImpl;

@Service("ApplicationDaoImpl")
@Transactional
public class ApplicationDaoImpl implements ApplicationDao {

	@Autowired
	private ApplicationContext context;
	
	public ApplicationDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createTable(Class<?> clazz) {
		// TODO Auto-generated method stub
		
	}

	@PostConstruct
    public final void init() throws IOException {
		Map<String, Storage> storageMap = context.getBeansOfType(Storage.class);
		for(String className: PropertiesLoaderUtils.loadAllProperties("schema.properties").getProperty("auto.create.table.class").split(",")){
			Storage storage = storageMap.get(className);
			if(storage != null) {
				try {
					storage.create();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
       
    }
}
