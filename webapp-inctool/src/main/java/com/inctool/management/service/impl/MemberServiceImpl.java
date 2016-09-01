package com.inctool.management.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.inctool.management.dao.MemberDao;
import com.inctool.management.enums.MemberEnum;
import com.inctool.management.form.AttendanceForm;
import com.inctool.management.form.MemberForm;
import com.inctool.management.mapper.MemberMapper;
import com.inctool.management.service.AttendanceService;
import com.inctool.management.service.MemberService;
import com.inctool.management.util.AttendanceUtil;

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
	public MemberForm save(MemberForm memberForm) throws Exception {
		// TODO Auto-generated method stub
		
		if(memberForm.getId() == null) {
			memberForm.setCreatedDate(DateFormatUtils.format(new Date(), AttendanceUtil.SIMPLE_DATE_PATTERN));
			memberForm.setStatus(MemberEnum.GUEST.toString());
		}
		if(memberForm.getR309().get(0).getDate() != null){
			if(memberForm.getR310().get(AttendanceUtil.R310_LIMIT-1).getDate() != null){
				memberForm.setStatus(MemberEnum.SK.toString());
			} else {
				memberForm.setStatus(MemberEnum.DK.toString());
			}
			
			AttendanceService  attendanceService = new R309AttendanceServiceImpl();
			memberForm.setR309(AttendanceUtil.fillSchedule(attendanceService, memberForm.getR309()));
			memberForm.setR309Attendance(AttendanceUtil.fillAttendance(attendanceService, new AttendanceForm(), memberForm.getR309()));
		}
		return memberMapper.map(memberDao.save(memberMapper.map(memberForm)));
	}
	@Override
	public List<MemberForm> getAll()  throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.mapForms(memberDao.findAll());
	}
	@Override
	public MemberForm get(String id)   throws Exception{
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
	public MemberForm findDetails(String id)   throws Exception{
		// TODO Auto-generated method stub
		return memberMapper.map(memberDao.findDetails(id));
	}
	@Override
	public void remove(String id)  throws Exception {
		// TODO Auto-generated method stub
		memberDao.remove(id);
	}

}
