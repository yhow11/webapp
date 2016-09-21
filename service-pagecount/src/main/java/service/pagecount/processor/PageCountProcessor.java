package service.pagecount.processor;

import common.query.QueryParam;
import service.metricmanagement.MetricSummaryService;
import service.metricmanagement.enums.MetricTypeEnum;
import service.metricmanagement.model.MetricSummaryModel;
import service.pagecount.PageCountService;
import service.pagecount.model.PageCountModel;
import service.urlmanagement.URLMetricService;
import service.urlmanagement.model.URLMetricModel;
import usertracker.browser.model.WebEventModel;
import usertracker.enums.WebEventTypeEnum;

public class PageCountProcessor {

	private URLMetricService URLMetricService;
	private PageCountService pageCountService;
	private MetricSummaryService metricSummaryService;
	
	public PageCountProcessor(URLMetricService URLMetricService, PageCountService pageCountService, MetricSummaryService metricSummaryService) {
		// TODO Auto-generated constructor stub
		this.URLMetricService = URLMetricService;
		this.pageCountService = pageCountService;
		this.metricSummaryService = metricSummaryService;
	}
	
	public void process(String visitorID, WebEventModel webEvent) throws Exception{
		if(WebEventTypeEnum.VISITED.getType().equals(webEvent.getType())){
			for(URLMetricModel urlMetricModel: URLMetricService.getAll(webEvent.getUrl(), MetricTypeEnum.PAGE_COUNT)){
				
				QueryParam<PageCountModel> param = new QueryParam<>(PageCountModel.class);
				param.getModel().setVISITORID(visitorID);
				param.getModel().setTKEY(urlMetricModel.getTKEY());
				param.getModel().setMETRIC(urlMetricModel.getMETRIC());
				param.getModel().setURL(urlMetricModel.getURL());
				param.getModel().setTVALUES(urlMetricModel.getTVALUES());
				PageCountModel pageCountModel = pageCountService.get(param);
				if(pageCountModel != null){
					pageCountModel.setTCOUNT(pageCountModel.getTCOUNT()+1);
					pageCountService.save(pageCountModel);
				} else {
					param.getModel().setTCOUNT(1L);
					pageCountService.save(param.getModel());
				}
				PageCountModel highest= pageCountService.getHighest(visitorID, param.getModel().getMETRIC());
				MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
				metricSummaryModel.setVISITORID(visitorID);
				metricSummaryModel.setMETRICNAME(param.getModel().getMETRIC());
				metricSummaryModel.setMETRICTYPE(MetricTypeEnum.PAGE_COUNT.getType());
				metricSummaryModel.setTVALUES(highest.getTVALUES());
				metricSummaryService.save(metricSummaryModel);
			}
		}
		
	}
}