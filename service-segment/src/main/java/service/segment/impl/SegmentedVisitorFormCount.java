package service.segment.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.mapper.Mapper;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.segment.form.SegmentedVisitorForm;
import service.segment.model.SegmentedVisitorModel;

@Service("SegmentedVisitorFormCount")
@Transactional
public class SegmentedVisitorFormCount implements Count<SegmentedVisitorForm> {


	@Resource(name="${SegmentedVisitorFormCount.aggregator}")
	private Count<SegmentedVisitorModel> aggregator;
	@Resource(name="${SegmentedVisitorFormCount.mapper}")
	private Mapper<SegmentedVisitorModel, SegmentedVisitorForm> mapper;
	
	@Override
	public Long count(Param<SegmentedVisitorForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(mapper.marshall(param, new DefaultParam<>(SegmentedVisitorModel.class)));
	}

}
