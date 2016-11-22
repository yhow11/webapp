package service.segment.processor;

import java.util.List;

import common.LogMetaData;
import common.Loggable;
import common.Processor;
import service.segment.processor.param.VisitorSegmentorParam;

public interface VisitorSegmentor extends Processor<VisitorSegmentorParam, Boolean> {

	@Loggable
	default void process(List<VisitorSegmentorParam> params, LogMetaData lmd) throws Exception {
		for(VisitorSegmentorParam param: params){
			process(param, lmd);
		}
	}
}
