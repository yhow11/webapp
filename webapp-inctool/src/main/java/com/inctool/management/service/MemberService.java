package com.inctool.management.service;

import java.util.List;

import com.inctool.management.form.MemberForm;

public interface MemberService {

	public void remove(String id) throws Exception;
	public MemberForm save(MemberForm memberForm) throws Exception;
	public MemberForm get(String id) throws Exception;
	public MemberForm findDetails(String id) throws Exception;
	public List<MemberForm> getAll() throws Exception;
}
