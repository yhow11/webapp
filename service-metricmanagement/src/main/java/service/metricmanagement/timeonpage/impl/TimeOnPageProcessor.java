package service.metricmanagement.timeonpage.impl;

import common.URLUtil;
import common.query.QueryParam;
import service.metricmanagement.MetricProcessor;
import service.metricmanagement.MetricSummaryService;
import service.metricmanagement.MetricURLService;
import service.metricmanagement.enums.MetricTypeEnum;
import service.metricmanagement.model.MetricSummaryModel;
import service.metricmanagement.timeonpage.TimeOnPageMetricService;
import service.metricmanagement.timeonpage.model.TimeOnPageMetricModel;
import service.metricmanagement.timeonpage.param.TimeOnPageMetricParam;
import service.urlmanagement.model.URLMetricModel;

public class TimeOnPageProcessor implements MetricProcessor<TimeOnPageMetricParam>{

	private MetricURLService metricURLService;
	private TimeOnPageMetricService timeOnPageService;
	private MetricSummaryService metricSummaryService;
	
	public TimeOnPageProcessor(MetricURLService metricURLService, TimeOnPageMetricService timeOnPageService, MetricSummaryService metricSummaryService) {
		// TODO Auto-generated constructor stub
		this.metricURLService = metricURLService;
		this.timeOnPageService = timeOnPageService;
		this.metricSummaryService = metricSummaryService;
	}
	
	public void process(TimeOnPageMetricParam param) throws Exception{
		if("LEAVED".equals(param.getType())){
			for(URLMetricModel urlMetricModel: metricURLService.getAll(URLUtil.getRealURL(param.getUrl()), MetricTypeEnum.TIME_ON_PAGE)){
				
				QueryParam<TimeOnPageMetricModel> queryParam = new QueryParam<>(TimeOnPageMetricModel.class);
				queryParam.getModel().setVISITORID(param.getVisitorID());
				queryParam.getModel().setTKEY(urlMetricModel.getTKEY());
				queryParam.getModel().setMETRIC(urlMetricModel.getMETRIC());
				queryParam.getModel().setURL(urlMetricModel.getURL());
				queryParam.getModel().setTVALUES(urlMetricModel.getTVALUES());
				TimeOnPageMetricModel timeOnPageModel = timeOnPageService.get(queryParam);
				if(timeOnPageModel != null){
					timeOnPageModel.setTIMEONPAGE(timeOnPageModel.getTIMEONPAGE() + Long.valueOf(param.getElapsedTime()));
					timeOnPageService.save(timeOnPageModel);
				} else {
					queryParam.getModel().setTIMEONPAGE(Long.valueOf(param.getElapsedTime()));
					timeOnPageService.save(queryParam.getModel());
				}
				TimeOnPageMetricModel highest= timeOnPageService.getHighest(param.getElapsedTime(), queryParam.getModel().getMETRIC());
				MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
				metricSummaryModel.setVISITORID(param.getVisitorID());
				metricSummaryModel.setMETRICNAME(queryParam.getModel().getMETRIC());
				metricSummaryModel.setMETRICTYPE(MetricTypeEnum.TIME_ON_PAGE.getType());
				metricSummaryModel.setTVALUES(highest.getTVALUES());
				metricSummaryService.save(metricSummaryModel);
			}
		}
		
	}
}