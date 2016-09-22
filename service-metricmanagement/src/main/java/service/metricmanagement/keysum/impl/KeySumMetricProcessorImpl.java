package service.metricmanagement.keysum.impl;

import common.URLUtil;
import common.query.QueryParam;
import service.metricmanagement.MetricProcessor;
import service.metricmanagement.MetricSummaryService;
import service.metricmanagement.MetricURLService;
import service.metricmanagement.enums.MetricTypeEnum;
import service.metricmanagement.keysum.KeySumMetricService;
import service.metricmanagement.keysum.model.KeySumMetricModel;
import service.metricmanagement.keysum.param.KeySumMetricParam;
import service.metricmanagement.model.MetricSummaryModel;
import service.urlmanagement.model.URLMetricModel;

public class KeySumMetricProcessorImpl implements MetricProcessor<KeySumMetricParam> {

	private MetricURLService metricURLService;
	private KeySumMetricService keySumMetricService;
	private MetricSummaryService metricSummaryService;
	
	public KeySumMetricProcessorImpl(MetricURLService metricURLService, KeySumMetricService keySumMetricService, MetricSummaryService metricSummaryService) {
		// TODO Auto-generated constructor stub
		this.metricURLService = metricURLService;
		this.keySumMetricService = keySumMetricService;
		this.metricSummaryService = metricSummaryService;
	}
	
	public void process(KeySumMetricParam param) throws Exception{
		if("VISITED".equalsIgnoreCase(param.getType())){
			for(URLMetricModel urlMetricModel: metricURLService.getAll(URLUtil.getRealURL(param.getUrl()), MetricTypeEnum.KEY_SUM)){
				
				QueryParam<KeySumMetricModel> queryParam = new QueryParam<>(KeySumMetricModel.class);
				queryParam.getModel().setVISITORID(param.getVisitorID());
				queryParam.getModel().setTKEY(urlMetricModel.getTKEY());
				queryParam.getModel().setMETRIC(urlMetricModel.getMETRIC());
				queryParam.getModel().setURL(urlMetricModel.getURL());
				queryParam.getModel().setTVALUES(urlMetricModel.getTVALUES());
				keySumMetricService.save(queryParam.getModel());
			
				Long sum = keySumMetricService.getSum(param.getVisitorID(), queryParam.getModel().getMETRIC());
				MetricSummaryModel metricSummaryModel = new MetricSummaryModel();
				metricSummaryModel.setVISITORID(param.getVisitorID());
				metricSummaryModel.setMETRICNAME(queryParam.getModel().getMETRIC());
				metricSummaryModel.setMETRICTYPE(MetricTypeEnum.KEY_SUM.getType());
				metricSummaryModel.setTVALUES(String.valueOf(sum));
				metricSummaryService.save(metricSummaryModel);
			}
		}
		
	}
}