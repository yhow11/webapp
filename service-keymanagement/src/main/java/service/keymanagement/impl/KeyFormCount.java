package service.keymanagement.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.mapper.Mapper;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.keymanagement.form.KeyForm;
import service.keymanagement.model.KeyModel;

@Service("KeyFormCount")
@Transactional
public class KeyFormCount implements Count<KeyForm> {

	@Resource(name="${KeyFormCount.aggregator}")
	private Count<KeyModel> aggregator;
	@Resource(name="${KeyFormCount.mapper}")
	private Mapper<KeyModel, KeyForm> mapper;
	
	public KeyFormCount() {
	}

	@Override
	public Long count(Param<KeyForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(mapper.marshall(param, new DefaultParam<>(KeyModel.class)));
	}


}
