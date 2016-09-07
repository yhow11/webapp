package service.membermanagement.service;

import org.springframework.transaction.annotation.Transactional;

import common.service.JService;
import service.membermanagement.model.INCWorkerModel;

@Transactional
public interface INCWorkerService extends JService<INCWorkerModel, INCWorkerModel> {
}
