package service.metricmanagement.metrics.keysum.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.LogMetaData;
import common.Loggable;
import common.URLUtil;
import common.orm.aggregator.Sum;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.metricmanagement.metrics.MetricProcessor;
import service.metricmanagement.metrics.enums.MetricTypeEnum;
import service.metricmanagement.metrics.keysum.model.KeySumMetricModel;
import service.metricmanagement.metrics.param.MetricProcessorParam;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;
import service.metricmanagement.metricurl.model.MetricURLModel;

@Service("KeySumMetricProcessor")
@Transactional
public class KeySumMetricProcessor implements MetricProcessor {

	@Resource(name="${KeySumMetricProcessor.metricURLStorage}")
	private Storage<MetricURLModel> metricURLStorage;
	@Resource(name="${KeySumMetricProcessor.storage}")
	private Storage<KeySumMetricModel> storage;
	@Resource(name="${KeySumMetricProcessor.aggregator}")
	private Sum<KeySumMetricModel> aggregator;
	@Resource(name="${KeySumMetricProcessor.metricSummaryStorage}")
	private Storage<MetricSummaryModel> metricSummaryStorage;
	
	public KeySumMetricProcessor(
			
	) {
		
	}

	@Loggable
	@Override
	public void process(MetricProcessorParam param, LogMetaData lmd) {
		// TODO Auto-generated method stub
		if("VISITED".equalsIgnoreCase(param.getType())){
			try{
				Param<MetricURLModel> metricURLParam = new DefaultParam<>(MetricURLModel.class);
				metricURLParam.getModel().setURL(URLUtil.getRealURL(param.getUrl()));
				metricURLParam.getModel().setMETRICTYPE(MetricTypeEnum.KEY_SUM.getType());
				for(MetricURLModel urlMetricModel: metricURLStorage.get(metricURLParam)){
					
					Param<KeySumMetricModel> queryParam = new DefaultParam<>(KeySumMetricModel.class);
					queryParam.getModel().setVISITORID(param.getVisitorID());
					queryParam.getModel().setTKEY(urlMetricModel.getTKEY());
					queryParam.getModel().setMETRIC(urlMetricModel.getMETRIC());
					queryParam.getModel().setURL(urlMetricModel.getURL());
					queryParam.getModel().setTVALUES(urlMetricModel.getTVALUES());
					storage.save(queryParam.getModel());
				
					Param<KeySumMetricModel> aggParam = new DefaultParam<>(KeySumMetricModel.class);
					aggParam.getModel().setVISITORID(param.getVisitorID());
					aggParam.getModel().setMETRIC(queryParam.getModel().getMETRIC());
					Long sum = aggregator.sum(aggParam);
					
					MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
					metricSummaryModel.setVISITORID(param.getVisitorID());
					metricSummaryModel.setMETRICNAME(queryParam.getModel().getMETRIC());
					metricSummaryModel.setMETRICTYPE(MetricTypeEnum.KEY_SUM.getType());
					metricSummaryModel.setTVALUES(String.valueOf(sum));
					metricSummaryStorage.save(metricSummaryModel);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}