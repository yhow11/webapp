package service.timeonpage.processor;

import common.query.QueryParam;
import service.metricmanagement.MetricSummaryService;
import service.metricmanagement.enums.MetricTypeEnum;
import service.metricmanagement.model.MetricSummaryModel;
import service.timeonpage.TimeOnPageService;
import service.timeonpage.model.TimeOnPageModel;
import service.urlmanagement.URLMetricService;
import service.urlmanagement.model.URLMetricModel;
import usertracker.browser.model.WebEventModel;
import usertracker.enums.WebEventTypeEnum;

public class TimeOnPageProcessor {

	private URLMetricService urlMetricService;
	private TimeOnPageService timeOnPageService;
	private MetricSummaryService metricSummaryService;
	
	public TimeOnPageProcessor(URLMetricService urlMetricService, TimeOnPageService timeOnPageService, MetricSummaryService metricSummaryService) {
		// TODO Auto-generated constructor stub
		this.urlMetricService = urlMetricService;
		this.timeOnPageService = timeOnPageService;
		this.metricSummaryService = metricSummaryService;
	}
	
	public void process(String visitorID, WebEventModel webEvent) throws Exception{
		if(WebEventTypeEnum.LEAVED.getType().equals(webEvent.getType())){
			for(URLMetricModel urlMetricModel: urlMetricService.getAll(webEvent.getUrl(), MetricTypeEnum.TIME_ON_PAGE)){
				
				QueryParam<TimeOnPageModel> param = new QueryParam<>(TimeOnPageModel.class);
				param.getModel().setVISITORID(visitorID);
				param.getModel().setTKEY(urlMetricModel.getTKEY());
				param.getModel().setMETRIC(urlMetricModel.getMETRIC());
				param.getModel().setURL(urlMetricModel.getURL());
				param.getModel().setTVALUES(urlMetricModel.getTVALUES());
				TimeOnPageModel timeOnPageModel = timeOnPageService.get(param);
				if(timeOnPageModel != null){
					timeOnPageModel.setTIMEONPAGE(timeOnPageModel.getTIMEONPAGE() + Long.valueOf(webEvent.getElapsedTime()));
					timeOnPageService.save(timeOnPageModel);
				} else {
					param.getModel().setTIMEONPAGE(Long.valueOf(webEvent.getElapsedTime()));
					timeOnPageService.save(param.getModel());
				}
				TimeOnPageModel highest= timeOnPageService.getHighest(visitorID, param.getModel().getMETRIC());
				MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
				metricSummaryModel.setVISITORID(visitorID);
				metricSummaryModel.setMETRICNAME(param.getModel().getMETRIC());
				metricSummaryModel.setMETRICTYPE(MetricTypeEnum.TIME_ON_PAGE.getType());
				metricSummaryModel.setTVALUES(highest.getTVALUES());
				metricSummaryService.save(metricSummaryModel);
			}
		}
		
	}
}