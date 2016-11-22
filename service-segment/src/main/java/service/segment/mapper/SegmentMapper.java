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
import service.segment.model.SegmentModel;

@Service("SegmentMapper")
public class SegmentMapper extends Mapper<SegmentModel, SegmentForm>{

	@Resource(name="${SegmentMapper.metricStorage}")
	private Storage<MetricForm> metricStorage;
	@Resource(name="${SegmentMapper.filterStorage}")
	private Storage<FilterForm> filterStorage;
	
	public SegmentMapper() {
		super(SegmentModel.class, SegmentForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SegmentModel marshall(SegmentForm source, SegmentModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getId(), value -> target.setID(Long.valueOf(value)));
		ObjectUtil.isPresent(source.getName(), value -> target.setNAME(value));
		ObjectUtil.isPresent(source.getMetrics(), value -> target.setMETRICID(Long.valueOf(value.get(0).getId())));
		ObjectUtil.isPresent(source.getFilter(), value -> target.setFILTER(value.getType()));
		ObjectUtil.isPresent(source.getValue(), value -> target.setTVALUE(value));
		return target;
	}

	@Override
	public SegmentForm unmarshall(SegmentModel source, SegmentForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getID(), value -> target.setId(String.valueOf(value)));
		ObjectUtil.isPresent(source.getNAME(), value -> target.setName(value));
		ObjectUtil.isPresent(source.getMETRICID(), value -> {
			Param<MetricForm> param = new DefaultParam<>(MetricForm.class);
			param.getModel().setId(String.valueOf(value));
			target.setMetrics(metricStorage.get(param));
			target.setId(String.valueOf(value));
		});
		ObjectUtil.isPresent(source.getFILTER(), value -> {
			Param<FilterForm> param = new DefaultParam<>(FilterForm.class);
			param.getModel().setType(value);
			target.setFilter(filterStorage.get(param).get(0));
		});
		ObjectUtil.isPresent(source.getTVALUE(), value -> target.setValue(value));
		return target;
	}

}
