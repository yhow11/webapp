package com.inctool.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.form.RequestForm;
import common.form.ResponseForm;
import common.form.Page;
import inc.member.form.MemberForm;
import inc.member.service.MemberService;

@Controller
@RequestMapping("api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.POST)
    public @ResponseBody Page<MemberForm> getAll(@RequestBody RequestForm<MemberForm> param) throws Exception {
        return memberService.query().getAllWithPagination(param);
    }
	@RequestMapping(value = "get", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> get(@RequestParam("id") String id) throws Exception {
        return new ResponseForm<MemberForm>(memberService.query().get(id));
    }
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> remove(@RequestParam("id") String id) {
        try {
        	memberService.command().remove(memberService.query().get(id));
        } catch (Exception e) {
        	return new ResponseForm<>(false, "UNABLE TO REMOVE MEMBER "+id);
        }
        return new ResponseForm<>();
    }
	@RequestMapping(value = "getTemplate", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> getTemplate() throws Exception {
        return new ResponseForm<MemberForm>(memberService.query().get(""));
    }
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> get(@RequestBody MemberForm memberForm) throws Exception {
		return new ResponseForm<>(memberService.command().save(memberForm));
    }
}
