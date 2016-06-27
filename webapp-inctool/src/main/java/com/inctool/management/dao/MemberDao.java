package com.inctool.management.dao;

import java.text.ParseException;
import java.util.List;

import com.inctool.management.model.MemberModel;

public interface MemberDao {

	public void remove(String id);
	public MemberModel save(MemberModel memberModel);
	public MemberModel findOne(String id);
	public MemberModel findDetails(String id);
	public List<MemberModel> findAll() throws ParseException;
}
