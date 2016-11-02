package service.metricmanagement.metric.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.Book;
import common.orm.query.Storage;
import service.metricmanagement.metric.form.MetricForm;

@Service("MetricFormBook")
@Transactional
public class MetricFormBook implements Book<MetricForm> {

	@Resource(name="${MetricFormBook.storage}")
	private Storage<MetricForm> storage;
	@Resource(name="${MetricFormBook.aggregator}")
	private Count<MetricForm> aggregator;
	
	public MetricFormBook() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Storage<MetricForm> storage() throws Exception {
		// TODO Auto-generated method stub
		return storage;
	}
	@Override
	public Count<MetricForm> aggregator() throws Exception {
		// TODO Auto-generated method stub
		return aggregator;
	}
	@Override
	public Class<MetricForm> clazz() {
		// TODO Auto-generated method stub
		return MetricForm.class;
	}
	

}
