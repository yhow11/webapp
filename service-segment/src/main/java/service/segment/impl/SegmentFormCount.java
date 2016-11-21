package service.segment.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.mapper.Mapper;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.segment.form.SegmentForm;
import service.segment.model.SegmentModel;

@Service("SegmentFormCount")
@Transactional
public class SegmentFormCount implements Count<SegmentForm> {


	@Resource(name="${SegmentFormCount.aggregator}")
	private Count<SegmentModel> aggregator;
	@Resource(name="${SegmentFormCount.mapper}")
	private Mapper<SegmentModel, SegmentForm> mapper;
	
	@Override
	public Long count(Param<SegmentForm> param) throws Exception {
		// TODO Auto-generated method stub
		return aggregator.count(mapper.marshall(param, new DefaultParam<>(SegmentModel.class)));
	}

}
