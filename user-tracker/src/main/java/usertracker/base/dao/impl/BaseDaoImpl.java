package usertracker.base.dao.impl;

import hbase.dao.HBaseDao;

public class BaseDaoImpl {

protected HBaseDao hbaseDao;
	
	public BaseDaoImpl(HBaseDao hbaseDao) {
		// TODO Auto-generated constructor stub
		this.hbaseDao = hbaseDao;
	}
}
