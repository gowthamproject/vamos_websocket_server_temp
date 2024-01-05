package com.wipro.vamos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.EnterpriseEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.repository.EnterpriseRepository;

@Service
public class EnterpriseService {

	@Autowired
	EnterpriseRepository enterpriseRepository;

	public com.wipro.vamos.model.Enterprise getEnterpriseById(int ent_id) throws ResourceNotFoundException {
		Optional<EnterpriseEntity> enterpriseOptional = enterpriseRepository.findById(ent_id);
		if (!enterpriseOptional.isPresent())
			throw new ResourceNotFoundException("Enterprise not found for this id :: " + ent_id);
		return Mapper.enterpriseEntityToModel(enterpriseOptional.get());
	}

	public void saveEnterprise(com.wipro.vamos.model.Enterprise enterprise) {
		enterpriseRepository.save(Mapper.enterpriseModelToEntity(enterprise));
	}

	public List<com.wipro.vamos.model.Enterprise> getAllEnterprise() {
		return Mapper.enterpriseEntityToModelList(enterpriseRepository.findAll());
	}

	public long getEnterpriseCount() {
		return enterpriseRepository.count();
	}

}
