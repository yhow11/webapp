package service.metricmanagement.metricsummary.mapper;

import org.springframework.stereotype.Service;

import common.orm.mapper.Mapper;
import service.metricmanagement.metricsummary.form.MetricSummaryForm;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

@Service("MetricSummaryMapper")
public class MetricSummaryMapper extends Mapper<MetricSummaryModel, MetricSummaryForm> {

	public MetricSummaryMapper() {
		super(MetricSummaryModel.class, MetricSummaryForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MetricSummaryModel marshall(MetricSummaryForm e, MetricSummaryModel target) {
		// TODO Auto-generated method stub
		target.setVISITORID(e.getVisitorID());
		target.setMETRICNAME(e.getMetricName());
		target.setMETRICTYPE(e.getMetricType());
		target.setTVALUES(e.getValues());
		return target;
	}

	@Override
	public MetricSummaryForm unmarshall(MetricSummaryModel t, MetricSummaryForm target) {
		// TODO Auto-generated method stub
		target.setVisitorID(t.getVISITORID());
		target.setMetricName(t.getMETRICNAME());
		target.setMetricType(t.getMETRICTYPE());
		target.setValues(t.getTVALUES());
		return target;
	}


}
