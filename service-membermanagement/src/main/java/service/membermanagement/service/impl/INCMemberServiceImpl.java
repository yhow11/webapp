package service.membermanagement.service.impl;

import java.util.List;

import service.membermanagement.model.INCMemberModel;
import service.membermanagement.service.MemberService;
import helper.mongodb.dao.HelperMongodbDao;

public class INCMemberServiceImpl extends MemberService<INCMemberModel, INCMemberModel>{

	private HelperMongodbDao helperMongodbDao;
	
	public INCMemberServiceImpl(HelperMongodbDao HelperMongodbDao) {
		// TODO Auto-generated constructor stub
		this.helperMongodbDao = helperMongodbDao;
	}
	@Override
	public List<INCMemberModel> getAll(INCMemberModel e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<INCMemberModel> get(INCMemberModel e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INCMemberModel getOne(INCMemberModel e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INCMemberModel save(INCMemberModel e) {
		// TODO Auto-generated method stub
		return null;
	}

}
