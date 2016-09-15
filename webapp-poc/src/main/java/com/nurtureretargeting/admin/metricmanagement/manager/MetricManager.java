package com.nurtureretargeting.admin.metricmanagement.manager;

import java.util.List;

import org.apache.directory.api.util.Strings;

import com.nurtureretargeting.admin.metricmanagement.mapper.MetricMapper;
import com.nurtureretargeting.admin.metricmanagement.object.MetricForm;

import service.metricmanagement.MetricService;

public class MetricManager {

	private MetricService metricService;
	private MetricMapper metricMapper;
	
	public MetricManager(MetricService metricService, MetricMapper metricMapper) {
		// TODO Auto-generated constructor stub
		this.metricService = metricService;
		this.metricMapper = metricMapper;
	}
	
	public MetricForm save(MetricForm metricForm) throws Exception{
		return metricMapper.unmarshall(metricService.save(metricMapper.marshall(metricForm)));
	}
	public void remove(MetricForm metricForm) throws Exception{
		metricService.remove(metricMapper.marshall(metricForm));
	}
	public List<MetricForm> getAll(String offset, String limit) throws Exception{
		Long offsetLong = !Strings.isNotEmpty(offset)? Long.valueOf(offset): null;
		Long limitLong = !Strings.isNotEmpty(limit)? Long.valueOf(limit): null;
		return metricMapper.unmarshall(metricService.getAll(offsetLong, limitLong));
	}
}
