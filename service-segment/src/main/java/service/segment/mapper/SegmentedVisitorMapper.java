package service.segment.mapper;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.metricmanagement.metric.form.MetricForm;
import service.segment.filter.form.FilterForm;
import service.segment.form.SegmentForm;
import service.segment.form.SegmentedVisitorForm;
import service.segment.model.SegmentModel;
import service.segment.model.SegmentedVisitorModel;

@Service("SegmentedVisitorMapper")
public class SegmentedVisitorMapper extends Mapper<SegmentedVisitorModel, SegmentedVisitorForm>{

	
	public SegmentedVisitorMapper() {
		super(SegmentedVisitorModel.class, SegmentedVisitorForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SegmentedVisitorModel marshall(SegmentedVisitorForm source, SegmentedVisitorModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getSegmentID(), value -> target.setSEGMENTID(value));
		ObjectUtil.isPresent(source.getVisitorID(), value -> target.setVISITORID(value));
		return target;
	}

	@Override
	public SegmentedVisitorForm unmarshall(SegmentedVisitorModel source, SegmentedVisitorForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getSEGMENTID(), value -> target.setSegmentID(value));
		ObjectUtil.isPresent(source.getVISITORID(), value -> target.setVisitorID(value));
		return target;
	}

}
