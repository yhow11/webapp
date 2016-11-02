package service.metricmanagement.metricsummary.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.Book;
import common.orm.query.Storage;
import service.metricmanagement.metricsummary.form.MetricSummaryForm;

@Service("MetricSummaryFormBook")
@Transactional
public class MetricSummaryFormBook implements Book<MetricSummaryForm> {

	@Resource(name="${MetricSummaryFormBook.storage}")
	private Storage<MetricSummaryForm> storage;
	@Resource(name="${MetricSummaryFormBook.aggregator}")
	private Count<MetricSummaryForm> aggregator;
	
	public MetricSummaryFormBook() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Storage<MetricSummaryForm> storage() throws Exception {
		// TODO Auto-generated method stub
		return storage;
	}
	@Override
	public Count<MetricSummaryForm> aggregator() throws Exception {
		// TODO Auto-generated method stub
		return aggregator;
	}
	@Override
	public Class<MetricSummaryForm> clazz() {
		// TODO Auto-generated method stub
		return MetricSummaryForm.class;
	}
}
