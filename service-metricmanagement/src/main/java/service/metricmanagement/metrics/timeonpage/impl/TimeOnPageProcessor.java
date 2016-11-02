package service.metricmanagement.metrics.timeonpage.impl;


import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.URLUtil;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.metricmanagement.metrics.MetricProcessor;
import service.metricmanagement.metrics.enums.MetricTypeEnum;
import service.metricmanagement.metrics.param.MetricProcessorParam;
import service.metricmanagement.metrics.timeonpage.model.TimeOnPageMetricModel;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;
import service.metricmanagement.metricurl.model.MetricURLModel;

@Service("TimeOnPageProcessor")
@Transactional
public class TimeOnPageProcessor implements MetricProcessor{
	
	@Resource(name="${TimeOnPageProcessor.metricURLStorage}")
	private Storage<MetricURLModel> metricURLModelStorage;
	@Resource(name="${TimeOnPageProcessor.storage}")
	private Storage<TimeOnPageMetricModel> storage;
	@Resource(name="${TimeOnPageProcessor.aggregator}")
	private Storage<TimeOnPageMetricModel> aggregator;
	@Resource(name="${TimeOnPageProcessor.metricSummaryStorage}")
	private Storage<MetricSummaryModel> metricSummaryStorage;
	
	public TimeOnPageProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(MetricProcessorParam param) {
		// TODO Auto-generated method stub
		if("LEAVED".equalsIgnoreCase(param.getType())){
			try{
				Param<MetricURLModel> metricURLParam = new DefaultParam<>(MetricURLModel.class);
				metricURLParam.getModel().setURL(URLUtil.getRealURL(param.getUrl()));
				metricURLParam.getModel().setMETRICTYPE(MetricTypeEnum.TIME_ON_PAGE.getType());
				
				for(MetricURLModel urlMetricModel: metricURLModelStorage.get(metricURLParam)){
					
					Param<TimeOnPageMetricModel> queryParam = new DefaultParam<>(TimeOnPageMetricModel.class);
					queryParam.getModel().setVISITORID(param.getVisitorID());
					queryParam.getModel().setTKEY(urlMetricModel.getTKEY());
					queryParam.getModel().setMETRIC(urlMetricModel.getMETRIC());
					queryParam.getModel().setURL(urlMetricModel.getURL());
					queryParam.getModel().setTVALUES(urlMetricModel.getTVALUES());
					Optional<TimeOnPageMetricModel> timeOnPageModel = storage.get(queryParam).stream().findFirst();
					if(timeOnPageModel.isPresent()){
						timeOnPageModel.get().setTIMEONPAGE(timeOnPageModel.get().getTIMEONPAGE() + Long.valueOf(param.getElapsedTime()));
						storage.save(timeOnPageModel.get());
					} else {
						queryParam.getModel().setTIMEONPAGE(Long.valueOf(param.getElapsedTime()));
						storage.save(queryParam.getModel());
					}
					Param<TimeOnPageMetricModel> highestParam = new DefaultParam<>(TimeOnPageMetricModel.class);
					highestParam.getModel().setVISITORID(param.getVisitorID());
					highestParam.getModel().setMETRIC(queryParam.getModel().getMETRIC());
					Optional<TimeOnPageMetricModel> highest= aggregator.get(highestParam).stream().findFirst();
					MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
					metricSummaryModel.setVISITORID(param.getVisitorID());
					metricSummaryModel.setMETRICNAME(queryParam.getModel().getMETRIC());
					metricSummaryModel.setMETRICTYPE(MetricTypeEnum.TIME_ON_PAGE.getType());
					metricSummaryModel.setTVALUES(highest.get().getTVALUES());
					metricSummaryStorage.save(metricSummaryModel);
					System.out.println("TimeOnPage saved.");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
	}
}