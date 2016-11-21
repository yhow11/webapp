package service.segment.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.Book;
import common.orm.query.Storage;
import service.segment.form.SegmentForm;

@Service("SegmentFormBook")
@Transactional
public class SegmentFormBook implements Book<SegmentForm> {

	@Resource(name="${SegmentFormBook.aggregator}")
	private Count<SegmentForm> aggregator;
	@Resource(name="${SegmentFormBook.storage}")
	private Storage<SegmentForm> storage;
	
	@Override
	public Storage<SegmentForm> storage() throws Exception {
		// TODO Auto-generated method stub
		return storage;
	}
	@Override
	public Count<SegmentForm> aggregator() throws Exception {
		// TODO Auto-generated method stub
		return aggregator;
	}
	@Override
	public Class<SegmentForm> clazz() {
		// TODO Auto-generated method stub
		return SegmentForm.class;
	}

}
