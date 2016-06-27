package com.inctool.management.service;

import java.text.ParseException;
import java.util.List;

import com.inctool.management.object.MemberForm;

public interface MemberService {

	public void remove(String id);
	public MemberForm save(MemberForm memberForm);
	public MemberForm findOne(String id);
	public MemberForm findDetails(String id);
	public List<MemberForm> findAll() throws ParseException;
}
