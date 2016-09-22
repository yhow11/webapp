package service.metricmanagement.pagecount;

import common.query.QueryParam;
import common.service.JService;
import service.metricmanagement.pagecount.model.PageCountModel;

public interface PageCountService extends JService<PageCountModel, PageCountModel> {
	public PageCountModel get(QueryParam<PageCountModel> param) throws Exception;
	public PageCountModel getHighest(String visitorID, String metric) throws Exception;
}
