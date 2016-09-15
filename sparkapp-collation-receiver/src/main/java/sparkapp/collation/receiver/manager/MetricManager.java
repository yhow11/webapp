package sparkapp.collation.receiver.manager;

import java.util.ArrayList;
import java.util.List;

import service.metricmanagement.model.MetricModel;
import service.urlmanagement.URLTaggedService;
import service.urlmanagement.model.URLTaggedModel;
import  service.metricmanagement.MetricService;
public class MetricManager {

	private MetricService metricService;
	private URLTaggedService urlTaggedService;
	
	public MetricManager(MetricService metricService, URLTaggedService urlTaggedService) {
		// TODO Auto-generated constructor stub
		this.urlTaggedService = urlTaggedService;
		this.metricService = metricService;
	}
	
	public List<MetricModel> getAll(String url) throws Exception{
		List<MetricModel> metricModels = new ArrayList<>();
		List<URLTaggedModel> urlTaggedModels = urlTaggedService.getAll(url);
		for(URLTaggedModel urlTaggedModel: urlTaggedModels){
			metricModels.addAll(metricService.getAll(urlTaggedModel.gettKey()));
		}
		return metricModels;
	}
}
