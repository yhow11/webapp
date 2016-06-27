package com.jofel.service.management;

import com.jofel.service.management.param.JUserParam;
import com.jofel.service.management.response.JUserResponse;

public interface JUserService {
	
	public JUserResponse find(JUserParam param);
	public JUserResponse findOne(JUserParam param);
}
