package com.inctool.management.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.inctool.common.form.ActionForm;
import com.inctool.common.form.DateForm;
import com.inctool.common.form.ResponseForm;
import com.inctool.management.object.MemberForm;
import com.inctool.management.service.MemberService;

@Controller
@RequestMapping("api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "getAll", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> getAll() throws ParseException {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        response.setData(memberService.findAll());
        response.setMessage("SUCCESS");
        response.setStatus(true);
        return response;
    }
	@RequestMapping(value = "get", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<MemberForm> get(@RequestParam("id") String id) {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        if(id != null && id != "") {
        	List<MemberForm> memberForms = new ArrayList<>();
        	MemberForm memberForm = memberService.findOne(id);
        	MemberForm memberDetailsForm = memberService.findDetails(id);
        	memberForm.setAbsent(memberDetailsForm.getAbsent());
        	memberForm.setPresent(memberDetailsForm.getPresent());
        	memberForm.setCompletionDate(memberDetailsForm.getCompletionDate());
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
    public @ResponseBody ResponseForm<MemberForm> get(@RequestBody MemberForm memberForm) {
       
        ResponseForm<MemberForm> response = new ResponseForm<MemberForm>();
        List<MemberForm> memberForms = new ArrayList<>();
        memberForms.add(memberService.save(memberForm));
        response.setData(memberForms);
        response.setMessage("SUCCESS");
        response.setStatus(true);
        return response;
    }
	public List<MemberForm> members() {
		List<DateForm> r310 = new ArrayList<DateForm>();
		 DateForm dateForm = new DateForm();
		 for(int counter = 0; counter < 25; counter++) {
			 dateForm = new DateForm();
			 dateForm.setId(String.valueOf(counter));
			 dateForm.setDate(new Date());
			 r310.add(dateForm); 
		 }
		 List<DateForm> r305 = new ArrayList<DateForm>();
		 for(int counter = 0; counter < 15; counter++) {
			 dateForm = new DateForm();
			 dateForm.setId(String.valueOf(counter));
			 dateForm.setDate(new Date());
			 r305.add(dateForm);
		 }
		 List<DateForm> r309 = new ArrayList<DateForm>();
		 for(int counter = 0; counter < 48; counter++) {
			 dateForm = new DateForm();
			 dateForm.setId(String.valueOf(counter));
			 dateForm.setDate(new Date());
			 r309.add(dateForm); 
		 }
		 MemberForm memberForm = new MemberForm();
         memberForm.setArea("37");
         memberForm.setGroup("14");
         memberForm.setDcode("01105");
         memberForm.setLcode("002");
         memberForm.setReference("123");
         memberForm.setName("John Fel O. Maulion");	
         memberForm.setAddress("Quezon City");
         memberForm.setR310(r310);
         memberForm.setR305(r305);
         memberForm.setR309(r309);
         ActionForm action = new ActionForm();
         action.setName("Edit");
         List<ActionForm> actions = new ArrayList<ActionForm>();
         actions.add(action);
         memberForm.setActions(actions);
        List<MemberForm> memberForms = new ArrayList<MemberForm>();
        memberForms.add(memberForm);
	        
        return memberForms;   
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
        
        List<ActionForm> actions = new ArrayList<ActionForm>();
        ActionForm action = new ActionForm();
        action.setName("Edit");
        actions.add(action);
        action = new ActionForm();
        action.setName("Delete");
        actions.add(action);
        memberForm.setActions(actions);
       List<MemberForm> memberForms = new ArrayList<MemberForm>();
       memberForms.add(memberForm);
	        
       return memberForms;   
	}
}
