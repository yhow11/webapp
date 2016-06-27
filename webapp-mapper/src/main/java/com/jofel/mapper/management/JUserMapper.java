package com.jofel.mapper.management;

import java.util.ArrayList;
import java.util.List;

import com.jofel.data.management.model.JUserModel;
import com.jofel.object.management.JUserDTO;

public class JUserMapper {
	
	public JUserDTO map(JUserModel model) {
		JUserDTO dto = new JUserDTO();
		dto.setEmail(model.getEmail());
		dto.setName(model.getName());
		dto.setNumber(model.getNumber());
		dto.setPassword(model.getPassword());
		dto.setUsername(model.getUsername());
		return dto;
	}
	public List<JUserDTO> map(List<JUserModel> models) {
		List<JUserDTO> dTos = new ArrayList<JUserDTO>();
		for(JUserModel model: models) {
			dTos.add(this.map(model));
		}
		return dTos;
	}

}
