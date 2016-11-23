package com.nurtureretargeting.admin.segmentmanagement.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.Page;
import common.form.ResponseForm;
import common.orm.query.Book;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import common.orm.query.util.QueryUtil;
import service.segment.form.SegmentedVisitorForm;

@Controller
public class SegmentedVisitorController {

	@Resource(name="${SegmentedVisitorController.storage}")
	private Storage<SegmentedVisitorForm> storage;
	
	@Resource(name="${SegmentedVisitorController.book}")
	private Book<SegmentedVisitorForm> book;
	
	@RequestMapping(value = "segmentedvisitor/getBook", method = RequestMethod.GET)
	public @ResponseBody  Page<SegmentedVisitorForm> getBook(@RequestParam("segmentID") String segmentID, @RequestParam("page") String page, @RequestParam("limit") String limit) throws Exception {
		Param<SegmentedVisitorForm> param = new DefaultParam<>(SegmentedVisitorForm.class, QueryUtil.getOffset(page, limit).toString(), limit);
		param.getModel().setSegmentID(segmentID);
		return book.getPage(param);
	}
	@RequestMapping(value = "segmentedvisitor/get", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<SegmentedVisitorForm> get(@RequestParam("id") String id) throws Exception {
		Param<SegmentedVisitorForm> param = new DefaultParam<>(SegmentedVisitorForm.class);
		param.getModel().setSegmentID(id);
		param.setLimit(1L);
		List<SegmentedVisitorForm> result = storage.get(param);
		if(result.size() > 0) {
			return new ResponseForm<>(result.get(0));
		} else {
			return new ResponseForm<>(false, "No Data.");
		}
	}
	@RequestMapping(value = "segmentedvisitor/count", method = RequestMethod.GET)
	public @ResponseBody  Map<String, Object> count(@RequestParam("segmentID") String segmentID) throws Exception {
		SegmentedVisitorForm form = new SegmentedVisitorForm();
		form.setSegmentID(segmentID);
		return  Collections.singletonMap("count", book.count(form));
	}
}
