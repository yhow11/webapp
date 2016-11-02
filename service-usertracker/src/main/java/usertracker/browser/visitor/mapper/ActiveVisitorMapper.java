package usertracker.browser.visitor.mapper;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import usertracker.browser.visitor.form.ActiveVisitorForm;
import usertracker.browser.visitor.model.ActiveVisitorModel;

@Service("ActiveVisitorMapper")
@Transactional
public class ActiveVisitorMapper extends Mapper<ActiveVisitorModel, ActiveVisitorForm>{

	public ActiveVisitorMapper() {
		super(ActiveVisitorModel.class, ActiveVisitorForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActiveVisitorModel marshall(ActiveVisitorForm source, ActiveVisitorModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getId(), value -> target.setId(value));
		ObjectUtil.isPresent(source.getMetrics(), values -> target.setMetrics(String.join(",", values)));
		ObjectUtil.isPresent(source.getActivities(), values -> target.setActivities(String.join(",", values)));
		ObjectUtil.isPresent(source.getTimestamp(), value -> target.setTimestamp(Long.valueOf(value)));
		return target;
	}

	@Override
	public ActiveVisitorForm unmarshall(ActiveVisitorModel source, ActiveVisitorForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getId(), value -> target.setId(value));
		ObjectUtil.isPresent(source.getMetrics(), values -> target.setMetrics(Arrays.asList(values.split(","))));
		ObjectUtil.isPresent(source.getActivities(), values -> target.setActivities(Arrays.asList(values.split(","))));
		ObjectUtil.isPresent(source.getTimestamp(), value -> target.setTimestamp(String.valueOf(value)));
		return target;
	}

}
