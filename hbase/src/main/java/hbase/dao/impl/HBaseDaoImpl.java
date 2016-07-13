package hbase.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import hbase.annotation.HBaseTableAnnotation;
import hbase.dao.HBaseDao;

public class HBaseDaoImpl implements HBaseDao {
	private Connection conn;

	public HBaseDaoImpl(Configuration conf) throws IOException {
		// TODO Auto-generated constructor stub

		this.conn = ConnectionFactory.createConnection(conf);
	}

	/**
	 * Create a table
	 */
	public void creatTable(String table, String[] familys) throws Exception {
		TableName tableName = TableName.valueOf(table);
		Admin admin = conn.getAdmin();
		if (admin.tableExists(tableName)) {
			deleteTable(table);
			HTableDescriptor tableDesc = new HTableDescriptor(tableName);
			for (int i = 0; i < familys.length; i++) {
				tableDesc.addFamily(new HColumnDescriptor(familys[i]));
			}
			admin.createTable(tableDesc);
			System.out.println("create table " + tableName + " ok.");
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(tableName);
			for (int i = 0; i < familys.length; i++) {
				tableDesc.addFamily(new HColumnDescriptor(familys[i]));
			}
			admin.createTable(tableDesc);
			System.out.println("create table " + tableName + " ok.");
		}
	}

	/**
	 * Delete a table
	 */
	public void deleteTable(String table) throws Exception {
		try {
			TableName tableName = TableName.valueOf(table);
			Admin admin = conn.getAdmin();
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
			System.out.println("delete table " + tableName + " ok.");
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Put (or insert) a row
	 */
	public void addRecord(String tableName, String rowKey, String family, String qualifier, String value)
			throws Exception {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			Put put = new Put(Bytes.toBytes(rowKey));
			put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
			table.put(put);
			System.out.println("insert recored " + family + " to table " + tableName + " ok." + value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a row
	 */
	public void delRecord(String tableName, String rowKey) throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
		List<Delete> list = new ArrayList<Delete>();
		Delete del = new Delete(rowKey.getBytes());
		list.add(del);
		table.delete(list);
		System.out.println("del recored " + rowKey + " ok.");
	}

	/**
	 * Get a row
	 */
	public <T> T getOneRecord(Class<T> clazz, String rowKey) throws Exception {
		Table table = conn.getTable(TableName.valueOf(clazz.getAnnotation(HBaseTableAnnotation.class).tablename()));
		Get get = new Get(rowKey.getBytes());
		Result rs = table.get(get);
		if(rs != null) {
			return map(rs, clazz);
		} else {
			return null;
		}
		
	}

	/**
	 * Scan (or list) a table
	 */
	public <T> List<T>  find(String word, String family, String qualifier, Class<T> klazz) throws Exception{
		try {
			Table table = conn.getTable(TableName.valueOf(klazz.getAnnotation(HBaseTableAnnotation.class).tablename()));
			Filter valFilter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(qualifier),
					CompareOp.EQUAL, new SubstringComparator(word));
			Scan s = new Scan();
			s.setReversed(true);
			s.setFilter(valFilter);
			ResultScanner ss = table.getScanner(s);
			return map(ss, klazz);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private <T> List<T> map(ResultScanner ss, Class<T> clazz) throws Exception {
		Iterator<Result> iterator = ss.iterator();
		List<T> results = new ArrayList<T>();
		while (iterator.hasNext()) {
			results.add(map(iterator.next(), clazz));
		}
		return results;
	}
	private <T> T map(Result result, Class<T> clazz) throws Exception {
		
		if(result.listCells() != null && result.listCells().size() > 0){
			Object newInstance = clazz.newInstance();
			for (Cell cell : result.listCells()) {
				String qualifier = Bytes.toString(cell.getQualifier());
				if (qualifier.equals("linkCount")) {
					try {
						PropertyUtils.setProperty(newInstance, qualifier,
								Long.valueOf(Bytes.toString(cell.getValue())));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						PropertyUtils.setProperty(newInstance, qualifier, Bytes.toString(cell.getValue()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			PropertyUtils.setProperty(newInstance, "id", Bytes.toString(result.getRow()));
			return (T) newInstance;
		} else {
			return null;
		}
	}
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return conn;
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz, Integer limit, String startRow, String lastRow) throws Exception {
		try {
			Table table = conn.getTable(TableName.valueOf(clazz.getAnnotation(HBaseTableAnnotation.class).tablename()));
			Scan s = new Scan();
			s.setReversed(true);
			FilterList filterList = new FilterList();
			if(limit != null) {
				Filter filter = new PageFilter(limit);
				filterList.addFilter(filter);
			}
			if(startRow != null && startRow != ""){
				s.setStartRow(Bytes.toBytes(startRow));
			}
			if(lastRow != null && lastRow != ""){
				s.setStopRow(Bytes.toBytes(lastRow));
			}

			if(filterList.getFilters().size() > 0) {
				s.setFilter(filterList);
			}
			ResultScanner ss = table.getScanner(s);

			return map(ss, clazz);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
