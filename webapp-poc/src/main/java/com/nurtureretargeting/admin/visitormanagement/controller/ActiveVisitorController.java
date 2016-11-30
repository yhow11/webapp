package com.nurtureretargeting.admin.visitormanagement.controller;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.Page;
import common.orm.query.Book;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import common.orm.query.util.QueryUtil;
import usertracker.browser.visitor.enums.ActiveVisitorFilterTypeEnum;
import usertracker.browser.visitor.form.ActiveVisitorForm;

@Controller
public class ActiveVisitorController {

	@Resource(name="${ActiveVisitorController.book}")
	private Book<ActiveVisitorForm> book;
	
	
	@RequestMapping(value = "activevisitor/getPage", method = RequestMethod.GET)
	public @ResponseBody  Page<ActiveVisitorForm> getPage(@RequestParam("value") String value,
			@RequestParam("page") String page, @RequestParam("limit") String limit) throws Exception {
		Param<ActiveVisitorForm> param = new DefaultParam<ActiveVisitorForm>(ActiveVisitorForm.class, QueryUtil.getOffset(page, limit).toString(), limit);
		if(!value.equals("ALL"))
			param.getConditions().add(" TIMESTAMP >= "+ActiveVisitorFilterTypeEnum.valueOf(value).getTime());
		Page<ActiveVisitorForm> pageForm = book.getPage(param);
		pageForm.getData().stream().forEach(item ->{
			item.setTimeago(ActiveVisitorFilterTypeEnum.valueOf(value).getTimeAgo(item.getTimestamp()));
		});
		return pageForm;
	}
	
	@RequestMapping(value = "activevisitor/count", method = RequestMethod.GET)
	public @ResponseBody  Map<String, Object> count(@RequestParam("value") String value) throws Exception {
		ActiveVisitorForm form = new ActiveVisitorForm();
		return Collections.singletonMap("count", book.count(form));
	}
}
