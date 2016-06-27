package com.inctool.management.mapper;

import java.util.List;

import com.inctool.management.model.MemberModel;
import com.inctool.management.object.MemberForm;

public interface MemberMapper {

	public MemberModel map(MemberForm memberForm);
	public MemberForm map(MemberModel memberModel);
	public List<MemberModel> mapModels(List<MemberForm> memberForms);
	public List<MemberForm> mapForms(List<MemberModel> memberModel);
}
