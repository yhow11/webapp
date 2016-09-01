package com.inctool.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inctool.common.form.DateForm;
import com.inctool.common.form.OptionForm;
import com.inctool.common.form.ResponseForm;
import com.inctool.management.form.MemberForm;
import com.inctool.management.service.MemberService;

@Controller
@RequestMapping("api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> getAll() throws Exception {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        response.setData(memberService.getAll());
        response.setMessage("SUCCESS");
        response.setStatus(true);
        return response;
    }
	@RequestMapping(value = "get", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> get(@RequestParam("id") String id) throws Exception {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        if(id != null && id != "") {
        	List<MemberForm> memberForms = new ArrayList<>();
        	MemberForm memberForm = memberService.get(id);
        	memberForms.add(memberForm);
        	response.setData(memberForms);
        } else {
        	response.setData(getMemberTemplate());
        }
        
        response.setMessage("SUCCESS");
        response.setStatus(true);
        return response;
    }
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> remove(@RequestParam("id") String id) {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        response.setMessage("SUCCESS");
        response.setStatus(true);
        try {
        	memberService.remove(id);
        } catch (Exception e) {
        	response.setMessage("FAILED");
        	 response.setStatus(false);
        }
        return response;
    }
	@RequestMapping(value = "getTemplate", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> getTemplate() {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        response.setData(getMemberTemplate());
        response.setMessage("SUCCESS");
        response.setStatus(true);
        return response;
    }
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> get(@RequestBody MemberForm memberForm) throws Exception {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        List<MemberForm> memberForms = new ArrayList<>();
        memberForms.add(memberService.save(memberForm));
        response.setData(memberForms);
        response.setMessage("SUCCESS");
        response.setStatus(true);
        return response;
    }
	public List<MemberForm> getMemberTemplate() {
		 List<DateForm> r310 = new ArrayList<DateForm>();
		 DateForm dateForm = new DateForm();
		 for(int counter = 0; counter < 25; counter++) {
			 dateForm = new DateForm();
			 dateForm.setId(String.valueOf(counter));
//			 dateForm.setDate(new Date());
			 r310.add(dateForm); 
		 }
		 List<DateForm> r305 = new ArrayList<DateForm>();
		 for(int counter = 0; counter < 15; counter++) {
			 dateForm = new DateForm();
			 dateForm.setId(String.valueOf(counter));
//			 dateForm.setDate(new Date());
			 r305.add(dateForm);
		 }
		 List<DateForm> r309 = new ArrayList<DateForm>();
		 for(int counter = 0; counter < 48; counter++) {
			 dateForm = new DateForm();
			 
			 dateForm.setId(String.valueOf(counter));
//			 dateForm.setDate(new Date());
			 r309.add(dateForm); 
		 }
		MemberForm memberForm = new MemberForm();
        memberForm.setR310(r310);
        memberForm.setR305(r305);
        memberForm.setR309(r309);
        
        List<OptionForm> actions = new ArrayList<OptionForm>();
        OptionForm action = new OptionForm();
        action.setName("Edit");
        actions.add(action);
        action = new OptionForm();
        action.setName("Delete");
        actions.add(action);
        memberForm.setOptions(actions);
       List<MemberForm> memberForms = new ArrayList<MemberForm>();
       memberForms.add(memberForm);
	        
       return memberForms;   
	}
}
