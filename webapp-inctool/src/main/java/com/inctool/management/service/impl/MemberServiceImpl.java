package com.inctool.management.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.inctool.management.dao.MemberDao;
import com.inctool.management.mapper.MemberMapper;
import com.inctool.management.model.MemberModel;
import com.inctool.management.object.MemberForm;
import com.inctool.management.service.MemberService;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;
	private MemberMapper memberMapper;
	
	public MemberServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	public MemberServiceImpl(MemberDao memberDao, MemberMapper memberMapper) {
		// TODO Auto-generated constructor stub
		this.memberDao = memberDao;
		this.memberMapper = memberMapper;
	}
	@Override
	public MemberForm save(MemberForm memberForm) {
		// TODO Auto-generated method stub
		if(memberForm.getCreatedDate() == null) {
			memberForm.setCreatedDate(new Date());
		}
		return memberMapper.map(memberDao.save(memberMapper.map(memberForm)));
	}
	@Override
	public List<MemberForm> findAll() throws ParseException {
		// TODO Auto-generated method stub
		return memberMapper.mapForms(memberDao.findAll());
	}
	@Override
	public MemberForm findOne(String id) {
		// TODO Auto-generated method stub
		return memberMapper.map(memberDao.findOne(id));
	}
	
	public static void main(String []args) {
		Calendar c = Calendar.getInstance();
	    while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        c.add(Calendar.DATE, -1);
	    }
	    System.out.println(DateFormatUtils.format(c, "yyyy MM dd"));
		for(int x = 0; x < 24 ; x++){
			c.add(Calendar.DATE, 7); 
			System.out.println(DateFormatUtils.format(c, "yyyy MM dd"));
		}
		
		
	}
	@Override
	public MemberForm findDetails(String id) {
		// TODO Auto-generated method stub
		return memberMapper.map(memberDao.findDetails(id));
	}
	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		memberDao.remove(id);
	}

}
