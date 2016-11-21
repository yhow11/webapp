package sparkapp.collation.receiver.service;

import java.util.List;

import common.Processor;
import usertracker.browser.visitorlog.model.VisitorLogModel;
import usertracker.browser.webevent.model.WebEventModel;

public interface LogProcessor extends Processor<List<VisitorLogModel>, List<WebEventModel>>{

}
