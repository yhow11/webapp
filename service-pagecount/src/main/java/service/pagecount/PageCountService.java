package service.pagecount;

import common.query.QueryParam;
import common.service.JService;
import service.pagecount.model.PageCountModel;

public interface PageCountService extends JService<PageCountModel, PageCountModel> {
	public PageCountModel get(QueryParam<PageCountModel> param) throws Exception;
}
