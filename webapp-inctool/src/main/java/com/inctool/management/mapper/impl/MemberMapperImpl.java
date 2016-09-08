package com.inctool.management.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.inctool.common.form.DateForm;
import com.inctool.common.form.OptionForm;
import com.inctool.management.form.AttendanceForm;
import com.inctool.management.form.MemberForm;
import com.inctool.management.mapper.MemberMapper;

import common.mapper.util.MapUtil;
import service.membermanagement.model.AttendanceModel;
import service.membermanagement.model.DateModel;
import service.membermanagement.model.INCMemberModel;
import service.membermanagement.model.OptionModel;

public class MemberMapperImpl implements MemberMapper {

	@Override
	public INCMemberModel marshall(MemberForm memberForm) throws Exception {
		// TODO Auto-generated method stub
		if(memberForm != null) {
			INCMemberModel model = MapUtil.map(INCMemberModel.class, memberForm);

			model.setR309Attendance(MapUtil.map(AttendanceModel.class, memberForm.getR309Attendance()));

			for (OptionForm optionForm : memberForm.getOptions()) {
				model.getOptions().add(MapUtil.map(OptionModel.class, optionForm));
			}
			for (DateForm dateForm : memberForm.getR305()) {
				model.getR305().add(MapUtil.map(DateModel.class, dateForm));
			}
			for (DateForm dateForm : memberForm.getR309()) {
				model.getR309().add(MapUtil.map(DateModel.class, dateForm));
			}
			for (DateForm dateForm : memberForm.getR310()) {
				model.getR310().add(MapUtil.map(DateModel.class, dateForm));
			}
			return model;
		} else {
			return null;
		}
		
	}

	@Override
	public MemberForm unmarshall(INCMemberModel memberModel) throws Exception {
		// TODO Auto-generated method stub
		if(memberModel != null) {
			MemberForm form = MapUtil.map(MemberForm.class, memberModel);

			form.setR309Attendance(MapUtil.map(AttendanceForm.class, memberModel.getR309Attendance()));

			for (OptionModel actionModel : memberModel.getOptions()) {
				form.getOptions().add(MapUtil.map(OptionForm.class, actionModel));
			}
			for (DateModel dateModel : memberModel.getR305()) {
				form.getR305().add(MapUtil.map(DateForm.class, dateModel));
			}
			for (DateModel dateModel : memberModel.getR309()) {
				form.getR309().add(MapUtil.map(DateForm.class, dateModel));
			}
			for (DateModel dateModel : memberModel.getR310()) {
				form.getR310().add(MapUtil.map(DateForm.class, dateModel));
			}
			return form;
		} else {
			return null;
		}
		
	}

	@Override
	public List<INCMemberModel> marshall(List<MemberForm> memberForms) throws Exception {
		// TODO Auto-generated method stub
		List<INCMemberModel> memberModels = new ArrayList<>();
		for (MemberForm memberForm : memberForms) {
			memberModels.add(this.marshall(memberForm));
		}
		return memberModels;
	}

	@Override
	public List<MemberForm> unmarshall(List<INCMemberModel> incModels) throws Exception {
		// TODO Auto-generated method stub
		List<MemberForm> memberForms = new ArrayList<>();
		for (INCMemberModel memberModel : incModels) {
			memberForms.add(this.unmarshall(memberModel));
		}
		return memberForms;
	}

	@Override
	public INCMemberModel marshall(MemberForm e, INCMemberModel target) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberForm unmarshall(INCMemberModel t, MemberForm target) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
