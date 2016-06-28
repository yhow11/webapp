package usertracker.browser.mapper.impl;

import java.io.Serializable;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import usertracker.browser.mapper.VisitorLogMapper;
import usertracker.browser.model.VisitorLogModel;


public class VisitorLogResultMapper implements VisitorLogMapper<Result, VisitorLogModel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public VisitorLogModel map(Result t) throws Exception {
		// TODO Auto-generated method stub
		VisitorLogModel log = new VisitorLogModel();
		for(Cell cell :t.listCells()){
			String qualifier = Bytes.toString(cell.getQualifier());
			if(qualifier.equals("linkCount")) {
				try {
					PropertyUtils.setProperty(log, qualifier,Long.valueOf(Bytes.toString(cell.getValue())));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					PropertyUtils.setProperty(log, qualifier,Bytes.toString(cell.getValue()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return log;
	}

	@Override
	public Result unmap(VisitorLogModel e) {
		// TODO Auto-generated method stub
		return null;
	}

}
