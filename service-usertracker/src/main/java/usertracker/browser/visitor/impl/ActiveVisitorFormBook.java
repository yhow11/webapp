package usertracker.browser.visitor.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.Book;
import common.orm.query.Storage;
import usertracker.browser.visitor.form.ActiveVisitorForm;

@Service("ActiveVisitorFormBook")
@Transactional
public class ActiveVisitorFormBook implements Book<ActiveVisitorForm> {
	
	@Resource(name="${ActiveVisitorFormBook.storage}")
	private Storage<ActiveVisitorForm> storage;
	@Resource(name="${ActiveVisitorFormBook.aggregator}")
	private Count<ActiveVisitorForm> aggregator;
	
	public ActiveVisitorFormBook() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Storage<ActiveVisitorForm> storage() throws Exception {
		// TODO Auto-generated method stub
		return storage;
	}
	@Override
	public Count<ActiveVisitorForm> aggregator() throws Exception {
		// TODO Auto-generated method stub
		return aggregator;
	}
	@Override
	public Class<ActiveVisitorForm> clazz() {
		// TODO Auto-generated method stub
		return ActiveVisitorForm.class;
	}
}
