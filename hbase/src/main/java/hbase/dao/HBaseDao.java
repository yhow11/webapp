package hbase.dao;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.client.Connection;

public interface HBaseDao {

	public void creatTable(String table, String[] familys) throws Exception;

	public void deleteTable(String table) throws Exception;
	
	public void addRecord(String tableName, String rowKey,
            String family, String qualifier, String value) throws Exception;
	public void delRecord(String tableName, String rowKey)
            throws IOException;
	public <T> T getOneRecord (Class<T> clazz, String rowKey) throws Exception;
    public <T> List<T> getAll (Class<T> clazz, Integer limit, String startRow, String lastRow)  throws Exception ;
    public Connection getConnection();
    <T> List<T>  find(String word, String family, String qualifier, Class<T> klazz) throws Exception;
}
