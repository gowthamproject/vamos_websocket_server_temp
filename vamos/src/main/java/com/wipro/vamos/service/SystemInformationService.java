package com.wipro.vamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
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

}
