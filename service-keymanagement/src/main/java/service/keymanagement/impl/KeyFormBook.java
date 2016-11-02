package service.keymanagement.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.Book;
import common.orm.query.Storage;
import service.keymanagement.form.KeyForm;

@Service("KeyFormBook")
@Transactional
public class KeyFormBook implements Book<KeyForm> {
	
	@Resource(name="${KeyFormBook.storage}")
	private Storage<KeyForm> storage;
	@Resource(name="${KeyFormBook.aggregator}")
	private Count<KeyForm> aggregator;
	
	public KeyFormBook() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Storage<KeyForm> storage() throws Exception {
		// TODO Auto-generated method stub
		return storage;
	}
	@Override
	public Count<KeyForm> aggregator() throws Exception {
		// TODO Auto-generated method stub
		return aggregator;
	}
	@Override
	public Class<KeyForm> clazz() {
		// TODO Auto-generated method stub
		return KeyForm.class;
	}

}
