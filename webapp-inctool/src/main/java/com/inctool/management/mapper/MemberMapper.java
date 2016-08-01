package com.inctool.management.mapper;

import java.util.List;

import com.inctool.management.form.MemberForm;
import com.inctool.management.model.MemberModel;

public interface MemberMapper {

	public MemberModel map(MemberForm memberForm)  throws Exception;
	public MemberForm map(MemberModel memberModel)  throws Exception;
	public List<MemberModel> mapModels(List<MemberForm> memberForms)  throws Exception;
	public List<MemberForm> mapForms(List<MemberModel> memberModel)  throws Exception;
}
