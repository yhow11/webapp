package service.urlmanagement.url.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.Book;
import common.orm.query.Storage;
import service.urlmanagement.url.form.URLForm;

@Service("URLFormBook")
@Transactional
public class URLFormBook implements Book<URLForm> {

	@Resource(name="${URLFormBook.storage}")
	private Storage<URLForm> storage;
	@Resource(name="${URLFormBook.aggregator}")
	private Count<URLForm> aggregator;
	
	public URLFormBook() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Storage<URLForm> storage() throws Exception {
		// TODO Auto-generated method stub
		return storage;
	}
	@Override
	public Count<URLForm> aggregator() throws Exception {
		// TODO Auto-generated method stub
		return aggregator;
	}
	@Override
	public Class<URLForm> clazz() {
		// TODO Auto-generated method stub
		return URLForm.class;
	}

}
