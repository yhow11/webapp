package service.metricmanagement.metric.mapper;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import common.orm.mapper.Mapper;
import service.keymanagement.form.KeyForm;
import service.metricmanagement.metric.form.MetricForm;
import service.metricmanagement.metric.model.MetricModel;
import service.metricmanagement.metrics.enums.MetricTypeEnum;
import service.metricmanagement.metrictype.form.MetricTypeForm;

@Service("MetricMapper")
public class MetricMapper extends Mapper<MetricModel, MetricForm> {

	public MetricMapper() {
		super(MetricModel.class, MetricForm.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MetricModel marshall(MetricForm e, MetricModel target) {
		// TODO Auto-generated method stub
		if(!Strings.isNullOrEmpty(e.getId())){
			target.setID(Long.valueOf(e.getId()));
		}
		target.setNAME(e.getName());
		
		if(e.getKeys().size() > 0) {
			target.setTKEY(e.getKeys().get(0).getKey());
		}
		if(e.getTypes().size() > 0) {
			target.setTYPE(e.getTypes().get(0).getType());
		}
		return target;
	}

	@Override
	public MetricForm unmarshall(MetricModel t, MetricForm target) {
		// TODO Auto-generated method stub
		target.setId(String.valueOf(t.getID()));
		target.setName(t.getNAME());
		
		if(!Strings.isNullOrEmpty(t.getTKEY())){
			KeyForm keyForm = new KeyForm();
			keyForm.setKey(t.getTKEY());
			target.getKeys().add(keyForm);
		}
		if(!Strings.isNullOrEmpty(t.getTYPE())){
			MetricTypeForm metricTypeForm = new MetricTypeForm();
			metricTypeForm.setType(t.getTYPE());
			metricTypeForm.setName(MetricTypeEnum.getByType(t.getTYPE()).getName());
			target.getTypes().add(metricTypeForm);
		}
		return target;
	}

}
