package com.inctool.management.service;

import com.inctool.management.form.MemberForm;

import common.query.form.FormParam;
import common.query.form.FormResponse;

public interface MemberService {

	public void remove(String id) throws Exception;
	public MemberForm save(MemberForm memberForm) throws Exception;
	public MemberForm get(String id) throws Exception;
	public MemberForm findDetails(String id) throws Exception;
	public FormResponse<MemberForm> getAll(FormParam<MemberForm> param) throws Exception;
}
