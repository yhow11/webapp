package service.membermanagement.service;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import service.membermanagement.model.INCMemberModel;

@Transactional
public interface INCMemberService extends JService<INCMemberModel, INCMemberModel> {
}
