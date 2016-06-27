package com.fingerprint.event.mapper.impl;

import java.io.Serializable;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.fingerprint.event.mapper.JMapper;
import com.fingerprint.event.model.EventModel;


public class EventResultMapper implements JMapper<Result, EventModel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public EventModel map(Result t) throws Exception {
		// TODO Auto-generated method stub
		EventModel event = new EventModel();
		for(Cell cell :t.listCells()){
			String qualifier = Bytes.toString(cell.getQualifier());
			if(qualifier.equals("linkCount")) {
				try {
					PropertyUtils.setProperty(event, qualifier,Long.valueOf(Bytes.toString(cell.getValue())));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					PropertyUtils.setProperty(event, qualifier,Bytes.toString(cell.getValue()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return event;
	}

	@Override
	public Result unmap(EventModel e) {
		// TODO Auto-generated method stub
		return null;
	}

}
