package service.segment.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.Book;
import common.orm.query.Storage;
import service.segment.form.SegmentedVisitorForm;

@Service("SegmentedVisitorBook")
@Transactional
public class SegmentedVisitorBook implements Book<SegmentedVisitorForm> {

	@Resource(name="${SegmentedVisitorBook.aggregator}")
	private Count<SegmentedVisitorForm> aggregator;
	@Resource(name="${SegmentedVisitorBook.storage}")
	private Storage<SegmentedVisitorForm> storage;
	
	@Override
	public Storage<SegmentedVisitorForm> storage() throws Exception {
		// TODO Auto-generated method stub
		return storage;
	}
	@Override
	public Count<SegmentedVisitorForm> aggregator() throws Exception {
		// TODO Auto-generated method stub
		return aggregator;
	}
	@Override
	public Class<SegmentedVisitorForm> clazz() {
		// TODO Auto-generated method stub
		return SegmentedVisitorForm.class;
	}

}
