package com.nurtureretargeting.admin.metricmanagement.manager;

import java.util.List;

import com.google.common.base.Strings;
import com.nurtureretargeting.admin.metricmanagement.mapper.MetricSummaryMapper;
import com.nurtureretargeting.admin.metricmanagement.object.MetricSummaryForm;

import common.query.util.QueryUtil;
import service.metricmanagement.MetricSummaryService;

public class MetricSummaryManager {

	private MetricSummaryService metricSummaryService;
	private MetricSummaryMapper metricSummaryMapper;
	
	public MetricSummaryManager(MetricSummaryService metricSummaryService, MetricSummaryMapper metricSummaryMapper) {
		// TODO Auto-generated constructor stub
		this.metricSummaryService = metricSummaryService;
		this.metricSummaryMapper = metricSummaryMapper;
	}
	
	public List<MetricSummaryForm> getAll(String type,String offset, String limit) throws Exception{
		type = !Strings.isNullOrEmpty(type) ? type: null;
		Long offsetLong = !Strings.isNullOrEmpty(offset)? QueryUtil.getOffset(offset, limit): null;
		Long limitLong = !Strings.isNullOrEmpty(limit)? Long.valueOf(limit): null;
		return metricSummaryMapper.unmarshall(metricSummaryService.getAll(type, offsetLong, limitLong));
	}
}
