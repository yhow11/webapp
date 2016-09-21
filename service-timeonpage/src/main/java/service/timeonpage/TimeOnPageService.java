package service.timeonpage;

import common.query.QueryParam;
import common.service.JService;
import service.timeonpage.model.TimeOnPageModel;

public interface TimeOnPageService extends JService<TimeOnPageModel, TimeOnPageModel> {
	public TimeOnPageModel get(QueryParam<TimeOnPageModel> param) throws Exception;
	public TimeOnPageModel getHighest(String visitorID, String metric) throws Exception;
}
