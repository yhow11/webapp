package usertracker.browser.dao.impl;

import java.util.List;

import hbase.annotation.HBaseTableAnnotation;
import hbase.dao.HBaseDao;
import usertracker.base.dao.impl.BaseDaoImpl;
import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.model.VisitorLogModel;

public class VisitorLogDaoImpl extends BaseDaoImpl implements VisitorLogDao {

	public VisitorLogDaoImpl(HBaseDao hbaseDao) {
		super(hbaseDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<VisitorLogModel> find(String word, String column) throws Exception {
		// TODO Auto-generated method stub
		return hbaseDao.find(word, "metadata", column, VisitorLogModel.class);
	}

	@Override
	public List<VisitorLogModel> getAll(Class<VisitorLogModel> clazz, Integer limit, String startRow, String lastRow)
			throws Exception {
		// TODO Auto-generated method stub
		return hbaseDao.getAll(VisitorLogModel.class, limit, startRow, lastRow);
	}

	@Override
	public void creatTable() throws Exception {
		// TODO Auto-generated method stub
		String[] familys = { "metadata" };
		createTable(VisitorLogModel.class.getAnnotation(HBaseTableAnnotation.class).tablename(), familys);

	}

	@Override
	public void save(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		super.save(model, VisitorLogModel.class);
	}

}
