package com.inctool.management.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inctool.common.form.DateForm;
import com.inctool.common.form.OptionForm;
import com.inctool.common.model.OptionModel;
import com.inctool.common.model.DateModel;
import com.inctool.common.utilities.MapUtil;
import com.inctool.management.form.AttendanceForm;
import com.inctool.management.form.MemberForm;
import com.inctool.management.mapper.MemberMapper;
import com.inctool.management.model.AttendanceModel;
import com.inctool.management.model.MemberModel;

@Service
public class MemberMapperImpl implements MemberMapper {

	@Override
	public MemberModel map(MemberForm memberForm) throws Exception {
		// TODO Auto-generated method stub
		MemberModel model = MapUtil.map(MemberModel.class, memberForm);
		
		model.setR309Attendance(MapUtil.map(AttendanceModel.class, memberForm.getR309Attendance()));
		
		for(OptionForm optionForm: memberForm.getOptions()) {
			model.getOptions().add(MapUtil.map(OptionModel.class, optionForm));
		}
		for(DateForm dateForm: memberForm.getR305()) {
			model.getR305().add(MapUtil.map(DateModel.class, dateForm));
		}
		for(DateForm dateForm: memberForm.getR309()) {
			model.getR309().add(MapUtil.map(DateModel.class, dateForm));
		}
		for(DateForm dateForm: memberForm.getR310()) {
			model.getR310().add(MapUtil.map(DateModel.class, dateForm));
		}
		return model;
	}

	@Override
	public MemberForm map(MemberModel memberModel) throws Exception {
		// TODO Auto-generated method stub
		MemberForm form = MapUtil.map(MemberForm.class, memberModel);
		
		form.setR309Attendance(MapUtil.map(AttendanceForm.class, memberModel.getR309Attendance()));
		
		for(OptionModel actionModel: memberModel.getOptions()) {
			form.getOptions().add(MapUtil.map(OptionForm.class, actionModel));
		}
		for(DateModel dateModel: memberModel.getR305()) {
			form.getR305().add(MapUtil.map(DateForm.class, dateModel));
		}
		for(DateModel dateModel: memberModel.getR309()) {
			form.getR309().add(MapUtil.map(DateForm.class, dateModel));
		}
		for(DateModel dateModel: memberModel.getR310()) {
			form.getR310().add(MapUtil.map(DateForm.class, dateModel));
		}
		return form;
	}

	@Override
	public List<MemberModel> mapModels(List<MemberForm> memberForms) throws Exception {
		// TODO Auto-generated method stub
		List<MemberModel> memberModels = new ArrayList<>();
		for(MemberForm memberForm: memberForms) {
			memberModels.add(this.map(memberForm));
		}
		return memberModels;
	}

	@Override
	public List<MemberForm> mapForms(List<MemberModel> memberModels) throws Exception {
		// TODO Auto-generated method stub
		List<MemberForm> memberForms = new ArrayList<>();
		for(MemberModel memberModel: memberModels) {
			memberForms.add(this.map(memberModel));
		}
		return memberForms;
	}

}
