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
import service.segment.form.SegmentForm;

@Controller
public class SegmentController {

	@Resource(name="${SegmentController.storage}")
	private Storage<SegmentForm> storage;
	
	@Resource(name="${SegmentController.book}")
	private Book<SegmentForm> book;
	
	@RequestMapping(value = "segment/getBook", method = RequestMethod.GET)
	public @ResponseBody  Page<SegmentForm> getBook(@RequestParam("page") String page, @RequestParam("limit") String limit) throws Exception {
		return book.getPage(new DefaultParam<>(SegmentForm.class, QueryUtil.getOffset(page, limit).toString(), limit));
	}
	@RequestMapping(value = "segment/get", method = RequestMethod.GET)
	public @ResponseBody  ResponseForm<SegmentForm> get(@RequestParam("id") String id) throws Exception {
		Param<SegmentForm> param = new DefaultParam<>(SegmentForm.class);
		param.getModel().setId(id);
		param.setLimit(1L);
		List<SegmentForm> result = storage.get(param);
		if(result.size() > 0) {
			return new ResponseForm<>(result.get(0));
		} else {
			return new ResponseForm<>(false, "No Data.");
		}
	}
	@RequestMapping(value = "segment/save", method = RequestMethod.POST)
	public @ResponseBody  ResponseForm<SegmentForm> save(@RequestBody SegmentForm segmentForm) throws Exception {
		return new ResponseForm<>(storage.save(segmentForm));
	}
	@RequestMapping(value = "segment/count", method = RequestMethod.GET)
	public @ResponseBody  Map<String, Object> count(@RequestParam("query") String query) throws Exception {
		SegmentForm form = new SegmentForm();
		form.setName(query);
		return  Collections.singletonMap("count", book.count(form));
	}
}
