package service.metricmanagement.pagecount.impl;

import common.query.QueryParam;
import service.metricmanagement.MetricProcessor;
import service.metricmanagement.MetricSummaryService;
import service.metricmanagement.MetricURLService;
import service.metricmanagement.enums.MetricTypeEnum;
import service.metricmanagement.model.MetricSummaryModel;
import service.metricmanagement.pagecount.PageCountService;
import service.metricmanagement.pagecount.model.PageCountModel;
import service.metricmanagement.pagecount.param.PageCountMetricParam;
import service.urlmanagement.model.URLMetricModel;

public class PageCountProcessorImpl implements MetricProcessor<PageCountMetricParam>{

	private MetricURLService metricURLService;
	private PageCountService pageCountService;
	private MetricSummaryService metricSummaryService;
	
	public PageCountProcessorImpl(MetricURLService metricURLService, PageCountService pageCountService, MetricSummaryService metricSummaryService) {
		// TODO Auto-generated constructor stub
		this.metricURLService = metricURLService;
		this.pageCountService = pageCountService;
		this.metricSummaryService = metricSummaryService;
	}
	
	public void process(PageCountMetricParam param) throws Exception{
		if("VISITED".equals(param.getType())){
			for(URLMetricModel urlMetricModel: metricURLService.getAll(param.getUrl(), MetricTypeEnum.PAGE_COUNT)){
				
				QueryParam<PageCountModel> queryParam = new QueryParam<>(PageCountModel.class);
				queryParam.getModel().setVISITORID(param.getVisitorID());
				queryParam.getModel().setTKEY(urlMetricModel.getTKEY());
				queryParam.getModel().setMETRIC(urlMetricModel.getMETRIC());
				queryParam.getModel().setURL(urlMetricModel.getURL());
				queryParam.getModel().setTVALUES(urlMetricModel.getTVALUES());
				PageCountModel pageCountModel = pageCountService.get(queryParam);
				if(pageCountModel != null){
					pageCountModel.setTCOUNT(pageCountModel.getTCOUNT()+1);
					pageCountService.save(pageCountModel);
				} else {
					queryParam.getModel().setTCOUNT(1L);
					pageCountService.save(queryParam.getModel());
				}
				PageCountModel highest= pageCountService.getHighest(param.getVisitorID(), queryParam.getModel().getMETRIC());
				MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
				metricSummaryModel.setVISITORID(param.getVisitorID());
				metricSummaryModel.setMETRICNAME(queryParam.getModel().getMETRIC());
				metricSummaryModel.setMETRICTYPE(MetricTypeEnum.PAGE_COUNT.getType());
				metricSummaryModel.setTVALUES(highest.getTVALUES());
				metricSummaryService.save(metricSummaryModel);
			}
		}
		
	}
}