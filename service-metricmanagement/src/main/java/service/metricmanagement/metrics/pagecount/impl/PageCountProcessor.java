package service.metricmanagement.metrics.pagecount.impl;

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
import service.metricmanagement.metrics.pagecount.model.PageCountModel;
import service.metricmanagement.metrics.param.MetricProcessorParam;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;
import service.metricmanagement.metricurl.model.MetricURLModel;

@Service("PageCountProcessor")
@Transactional
public class PageCountProcessor implements MetricProcessor{

	@Resource(name="${PageCountProcessor.metricURLStorage}")
	private Storage<MetricURLModel> metricURLStorage;
	@Resource(name="${PageCountProcessor.storage}")
	private Storage<PageCountModel> storage;
	@Resource(name="${PageCountProcessor.aggregator}")
	private Storage<PageCountModel> aggregator;
	@Resource(name="${PageCountProcessor.metricSummaryStorage}")
	private Storage<MetricSummaryModel> metricSummaryStorage;
	
	public PageCountProcessor(
			) {
	}

	@Override
	public void process(MetricProcessorParam param) {
		// TODO Auto-generated method stub
		if("VISITED".equalsIgnoreCase(param.getType())){
			try{
				Param<MetricURLModel> metricURLParam = new DefaultParam<>(MetricURLModel.class);
				metricURLParam.getModel().setURL(URLUtil.getRealURL(param.getUrl()));
				metricURLParam.getModel().setMETRICTYPE(MetricTypeEnum.PAGE_COUNT.getType());
				for(MetricURLModel urlMetricModel: metricURLStorage.get(metricURLParam)){
					
					Param<PageCountModel> queryParam = new DefaultParam<>(PageCountModel.class);
					queryParam.getModel().setVISITORID(param.getVisitorID());
					queryParam.getModel().setTKEY(urlMetricModel.getTKEY());
					queryParam.getModel().setMETRIC(urlMetricModel.getMETRIC());
					queryParam.getModel().setURL(urlMetricModel.getURL());
					queryParam.getModel().setTVALUES(urlMetricModel.getTVALUES());
					Optional<PageCountModel> pageCountModel = storage.get(queryParam).stream().findFirst();
					
					if(pageCountModel.isPresent()){
						pageCountModel.get().setTCOUNT(pageCountModel.get().getTCOUNT()+1);
						storage.save(pageCountModel.get());
					} else {
						queryParam.getModel().setTCOUNT(1L);
						storage.save(queryParam.getModel());
					}
					Param<PageCountModel> highestParam = new DefaultParam<>(PageCountModel.class);
					highestParam.getModel().setVISITORID(param.getVisitorID());
					highestParam.getModel().setMETRIC(queryParam.getModel().getMETRIC());
					Optional<PageCountModel> highest= aggregator.get(highestParam).stream().findFirst();
					MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
					metricSummaryModel.setVISITORID(param.getVisitorID());
					metricSummaryModel.setMETRICNAME(queryParam.getModel().getMETRIC());
					metricSummaryModel.setMETRICTYPE(MetricTypeEnum.PAGE_COUNT.getType());
					metricSummaryModel.setTVALUES(highest.get().getTVALUES());
					metricSummaryStorage.save(metricSummaryModel);
					System.out.println("PageCount saved.");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}