package service.segment.processor.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.LogMetaData;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import service.segment.model.SegmentModel;
import service.segment.model.SegmentedVisitorModel;
import service.segment.processor.VisitorSegmentor;
import service.segment.processor.param.VisitorSegmentorParam;


@Service("DefaultVisitorSegmentor")
@Transactional
public class DefaultVisitorSegmentor implements VisitorSegmentor {

	@Resource(name="${DefaultVisitorSegmentor.segmentedVisitorStorage}")
	private Storage<SegmentedVisitorModel> segmentedVisitorStorage;
	@Resource(name="${DefaultVisitorSegmentor.segmentStorage}")
	private Storage<SegmentModel> segmentStorage;
	
	@Override
	public Boolean process(VisitorSegmentorParam param, LogMetaData lmd) throws Exception{
		// TODO Auto-generated method stub
		Param<SegmentModel> segmentParam = new DefaultParam<>(SegmentModel.class);
		segmentParam.getModel().setMETRICID(Long.valueOf(param.getMetricID()));
		List<SegmentModel> segments = segmentStorage.get(segmentParam);
		
		List<SegmentedVisitorModel> segmentedVisitors = new ArrayList<>();
		for(SegmentModel segment: segments){
			
			if(segment.getFILTER().equals("EQ") && segment.getTVALUE().equals(param.getValues())){
				SegmentedVisitorModel segmentedVisitor = new SegmentedVisitorModel();
				segmentedVisitor.setSEGMENTID(String.valueOf(segment.getID()));
				segmentedVisitor.setVISITORID(param.getVisitorID());
				segmentedVisitors.add(segmentedVisitor);
			} else if(segment.getFILTER().equals("NE") && !segment.getTVALUE().equals(param.getValues())){
				SegmentedVisitorModel segmentedVisitor = new SegmentedVisitorModel();
				segmentedVisitor.setSEGMENTID(String.valueOf(segment.getID()));
				segmentedVisitor.setVISITORID(param.getVisitorID());
				segmentedVisitors.add(segmentedVisitor);
			} else if(segment.getFILTER().equals("LT") && Long.valueOf(param.getValues()) < Long.valueOf(segment.getTVALUE())){
				SegmentedVisitorModel segmentedVisitor = new SegmentedVisitorModel();
				segmentedVisitor.setSEGMENTID(String.valueOf(segment.getID()));
				segmentedVisitor.setVISITORID(param.getVisitorID());
				segmentedVisitors.add(segmentedVisitor);
			} else if(segment.getFILTER().equals("GT") && Long.valueOf(param.getValues()) > Long.valueOf(segment.getTVALUE())){
				SegmentedVisitorModel segmentedVisitor = new SegmentedVisitorModel();
				segmentedVisitor.setSEGMENTID(String.valueOf(segment.getID()));
				segmentedVisitor.setVISITORID(param.getVisitorID());
				segmentedVisitors.add(segmentedVisitor);
				System.out.println("ES");
			}
			System.out.println(param.getValues()+"ASd"+segment.getTVALUE());
		}
		
		segmentedVisitorStorage.save(segmentedVisitors);
		return true;
	}
}
