package com.wipro.vamos.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.CPEEntity;
import com.wipro.vamos.entity.GNodeBEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.CPE;
import com.wipro.vamos.repository.CPERepository;
import com.wipro.vamos.response.CPECount;

@Service
public class CPEService {

	@Autowired
	private CPERepository cpeRepository;

	public List<CPE> getAllCPE() {
		return Mapper.cpeEntityToModelList(cpeRepository.findAll());
	}

	public CPE getCPEByID(long cpe_id) throws ResourceNotFoundException {
		Optional<CPEEntity> cpeOptional = cpeRepository.findById(cpe_id);
		if (!cpeOptional.isEmpty())
			throw new ResourceNotFoundException("CPE not found for this id :: " + cpe_id);

		return Mapper.cpeEntityToModel(cpeOptional.get());
	}

	public List<CPE> getCPEByGNodeBID(long gnb_id) throws ResourceNotFoundException {
		return Mapper.cpeEntityToModelList(cpeRepository.findByGNodeBId(gnb_id));
	}

	public CPECount getCPEStatusCountByGNodeBID(long gnb_id) {
		List<CPEEntity> cpeList = cpeRepository.findByGNodeBId(gnb_id);
		Map<String, Long> cpeCountByStatusMap = cpeList.stream()
				.collect(Collectors.groupingBy(CPEEntity::getStatus, Collectors.counting()));
		CPECount cpeResponse = new CPECount();
		cpeResponse.setGnb_id(gnb_id);
		cpeResponse.setCpeCountByStatusMap(cpeCountByStatusMap);
		return cpeResponse;
	}

	public void saveCPE(int gnb_id, CPE cpe) {
		CPEEntity cpeEntity = Mapper.cpeModelToEntity(cpe);
		GNodeBEntity gNodeBEntity = new GNodeBEntity();
		gNodeBEntity.setId(gnb_id);
		cpeEntity.setGNodeB(gNodeBEntity);
		cpeRepository.save(cpeEntity);
	}

	public void deleteCPEByGNodeB(Long gnb_id) {
		cpeRepository.deleteByGNodeBId(gnb_id);
		
	}

}
