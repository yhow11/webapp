package usertracker.browser.dao.impl;

import java.util.List;

import hbase.dao.HBaseDao;
import usertracker.base.dao.impl.BaseDaoImpl;
import usertracker.browser.dao.VisitorLogDao;

public class VisitorLogDaoImpl extends BaseDaoImpl implements VisitorLogDao {

	public VisitorLogDaoImpl(HBaseDao hbaseDao) {
		super(hbaseDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> List<T> find(Class<T> clazz, String word, String column) throws Exception {
		// TODO Auto-generated method stub
		return hbaseDao.find(word, "metadata", column, clazz);
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz, Integer limit, String startRow, String lastRow)
			throws Exception {
		// TODO Auto-generated method stub
		return hbaseDao.getAll(clazz, limit, startRow, lastRow);
	}

	@Override
	public void creatTable(String tableName) throws Exception {
		// TODO Auto-generated method stub
		String[] familys = { "metadata" };
		createTable(tableName, familys);

	}

	@Override
	public <T> T save(Class<T> clazz, Object object) throws Exception {
		// TODO Auto-generated method stub
		super.save(object, clazz);
		return (T) object;
	}

	@Override
	public <T> T getOne(Class<T> clazz, String id) throws Exception {
		// TODO Auto-generated method stub
		return hbaseDao.getOneRecord(clazz, id);
	}

}
