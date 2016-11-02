package usertracker.browser.visitor.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.mapper.Mapper;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import usertracker.browser.visitor.form.ActiveVisitorForm;
import usertracker.browser.visitor.model.ActiveVisitorModel;

@Service("ActiveVisitorFormCount")
@Transactional
public class ActiveVisitorFormCount  implements Count<ActiveVisitorForm> {

	@Resource(name="${ActiveVisitorFormCount.aggregator}")
	private Count<ActiveVisitorModel> aggregator;
	@Resource(name="${ActiveVisitorFormCount.mapper}")
	private Mapper<ActiveVisitorModel, ActiveVisitorForm> mapper;

	@Override
	public Long count(Param<ActiveVisitorForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(mapper.marshall(param, new DefaultParam<>(ActiveVisitorModel.class)));
	}


	

}
