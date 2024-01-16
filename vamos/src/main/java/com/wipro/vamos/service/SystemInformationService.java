package com.wipro.vamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.SystemInformationEntity;
import com.wipro.vamos.model.SystemInformation;
import com.wipro.vamos.repository.SystemInformationRepository;

@Service
public class SystemInformationService {

	@Autowired
	private SystemInformationRepository systemInformationRepository;

	public List<SystemInformation> getAllSystemInformation() {
		return Mapper.systemInformationEntityToModelList(systemInformationRepository.findAll());
	}

	public List<SystemInformation> getSystemInformationByCoreId(String core_id) {
		return Mapper.systemInformationEntityToModelList(systemInformationRepository.findByCore5GId(core_id));
	}

	public String saveInformation(SystemInformation systemInformation) {
		return null;
	}

	public String saveInformation(String core_id, SystemInformation systemInformation) {
		    SystemInformationEntity entity = Mapper.systemInformationModelToEntity(systemInformation);
			Core5GEntity core5gEntity = new Core5GEntity();
			core5gEntity.setId(core_id);
			entity.setCore5G(core5gEntity);
			systemInformationRepository.save(entity);
		return "SytemInformation Saved.!!";
	}

}
