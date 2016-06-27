package com.inctool.management.mapper.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.inctool.common.form.ActionForm;
import com.inctool.common.form.DateForm;
import com.inctool.common.model.ActionModel;
import com.inctool.common.model.DateModel;
import com.inctool.management.enums.MemberEnum;
import com.inctool.management.enums.WorshipServiceEnum;
import com.inctool.management.mapper.MemberMapper;
import com.inctool.management.model.MemberModel;
import com.inctool.management.object.MemberForm;
import com.inctool.management.util.ScheduleUtil;

@Service
public class MemberMapperImpl implements MemberMapper {

	@Override
	public MemberModel map(MemberForm memberForm) {
		// TODO Auto-generated method stub
		MemberModel model = new MemberModel();
		model.setId(memberForm.getId());
		model.setCreatedDate(memberForm.getCreatedDate());
		model.setAddress(memberForm.getAddress());
		model.setArea(memberForm.getArea());
		model.setDcode(memberForm.getDcode());
		model.setGroup(memberForm.getGroup());
		model.setLcode(memberForm.getLcode());
		model.setName(memberForm.getName());
		model.setReference(memberForm.getReference());
		model.setAbsent(Integer.valueOf(memberForm.getAbsent()));
		model.setPresent(Integer.valueOf(memberForm.getPresent()));
		model.setStatus(MemberEnum.valueOf(memberForm.getStatus()));
		List<List<Date>> worshipServiceSchedules = new ArrayList<>();
		
		if(memberForm.getR309().get(0).getDate() != null){
			model.setStatus(MemberEnum.DK);
			worshipServiceSchedules  = ScheduleUtil.getSchedules(memberForm.getR309().get(0).getDate());
		}
		
		if(worshipServiceSchedules.size() > 0) {
			Date completionDate = worshipServiceSchedules.get(worshipServiceSchedules.size()-1).get(1);
			model.setCompletionDate(ScheduleUtil.getCompletionDate(completionDate, model.getAbsent()));
		}
		for(ActionForm actionForm: memberForm.getActions()) {
			ActionModel actionModel = new ActionModel();
			actionModel.setName(actionForm.getName());
			model.getActions().add(actionModel);
		}
		for(DateForm dateForm: memberForm.getR305()) {
			DateModel dateModel = new DateModel();
			dateModel.setDate(dateForm.getDate());
			dateModel.setId(dateForm.getId());
			model.getR305().add(dateModel);
		}
		
		for(DateForm dateForm: memberForm.getR309()) {
			DateModel dateModel = new DateModel();
			dateModel.setDate(dateForm.getDate());
			dateModel.setId(dateForm.getId());
			if(worshipServiceSchedules.size() > 0){
				dateModel.setWeekStartDate(worshipServiceSchedules.get(Integer.valueOf(dateForm.getId())).get(0));
				dateModel.setWeekEndDate(worshipServiceSchedules.get(Integer.valueOf(dateForm.getId())).get(1));
			}
			dateModel.setWorshipServiceStatus(WorshipServiceEnum.valueOf(dateForm.getWorshipServiceStatus()));
			model.getR309().add(dateModel);
		}
		for(DateForm dateForm: memberForm.getR310()) {
			DateModel dateModel = new DateModel();
			dateModel.setDate(dateForm.getDate());
			dateModel.setId(dateForm.getId());
			model.getR310().add(dateModel);
		}
		return model;
	}

	@Override
	public MemberForm map(MemberModel memberModel) {
		// TODO Auto-generated method stub
		MemberForm form = new MemberForm();
		form.setId(memberModel.getId());
		form.setCreatedDate(memberModel.getCreatedDate());
		form.setAddress(memberModel.getAddress());
		form.setArea(memberModel.getArea());
		form.setDcode(memberModel.getDcode());
		form.setGroup(memberModel.getGroup());
		form.setLcode(memberModel.getLcode());
		form.setName(memberModel.getName());
		form.setReference(memberModel.getReference());
		form.setStatus(memberModel.getStatus().toString());
		form.setAbsent(String.valueOf(memberModel.getAbsent()));
		form.setPresent(String.valueOf(memberModel.getPresent()));
		form.setCompletionDate(memberModel.getCompletionDate());
		for(ActionModel actionModel: memberModel.getActions()) {
			ActionForm actionForm = new ActionForm();
			actionForm.setName(actionModel.getName());
			form.getActions().add(actionForm);
		}
		for(DateModel dateModel: memberModel.getR305()) {
			DateForm dateForm = new DateForm();
			dateForm.setDate(dateModel.getDate());
			dateForm.setId(dateModel.getId());
			form.getR305().add(dateForm);
		}
		for(DateModel dateModel: memberModel.getR309()) {
			DateForm dateForm = new DateForm();
			dateForm.setDate(dateModel.getDate());
			dateForm.setId(dateModel.getId());
			dateForm.setWorshipServiceStatus(dateModel.getWorshipServiceStatus().toString());
			dateForm.setWeekStartDate(dateModel.getWeekStartDate());
			dateForm.setWeekEndDate(dateModel.getWeekEndDate());
			form.getR309().add(dateForm);
		}
		for(DateModel dateModel: memberModel.getR310()) {
			DateForm dateForm = new DateForm();
			dateForm.setDate(dateModel.getDate());
			dateForm.setId(dateModel.getId());
			form.getR310().add(dateForm);
		}
		return form;
	}

	@Override
	public List<MemberModel> mapModels(List<MemberForm> memberForms) {
		// TODO Auto-generated method stub
		List<MemberModel> memberModels = new ArrayList<>();
		for(MemberForm memberForm: memberForms) {
			memberModels.add(this.map(memberForm));
		}
		return memberModels;
	}

	@Override
	public List<MemberForm> mapForms(List<MemberModel> memberModels) {
		// TODO Auto-generated method stub
		List<MemberForm> memberForms = new ArrayList<>();
		for(MemberModel memberModel: memberModels) {
			memberForms.add(this.map(memberModel));
		}
		return memberForms;
	}

}
