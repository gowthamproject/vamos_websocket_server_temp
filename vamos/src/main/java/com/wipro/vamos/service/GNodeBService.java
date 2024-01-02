package com.wipro.vamos.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.GNodeBEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.repository.GNodeBRepository;
import com.wipro.vamos.response.GNodeBResponse;

@Service
public class GNodeBService {

	@Autowired
	private GNodeBRepository gNodeBRepository;

	@Autowired
	private LocationService locationService;

	public List<GNodeBEntity> getAllGnodeB() {
		return gNodeBRepository.findAll();
	}

	public GNodeB getGNodeBByID(long gnb_id) throws ResourceNotFoundException {
		Optional<GNodeBEntity> gNodeBOptional = gNodeBRepository.findById(gnb_id);
		if (!gNodeBOptional.isPresent())
			throw new ResourceNotFoundException("gNB not found for this id :: " + gnb_id);
		return Mapper.gNodeBEntityToModel(gNodeBOptional.get());
	}

	public List<GNodeB> getGNodeBByCoreID(String core_id) throws ResourceNotFoundException {
		return Mapper.gNodeBEntityToModelList(gNodeBRepository.findByCore5GId(core_id));
	}

	public GNodeBResponse getGNodeBStatusCountByCoreId(String core_id) {
		List<GNodeBEntity> gnbList = gNodeBRepository.findByCore5GId(core_id);
		Map<String, Long> gNodeBCountByStatusMap = gnbList.stream()
				.collect(Collectors.groupingBy(GNodeBEntity::getStatus, Collectors.counting()));
		GNodeBResponse gNodeBResponse = new GNodeBResponse();
		gNodeBResponse.setNode_id(core_id);
		gNodeBResponse.setGNodeBCountByStatusMap(gNodeBCountByStatusMap);
		return gNodeBResponse;
	}

	public void saveGNodeB(String core_id, GNodeB gNodeB) {
		GNodeBEntity gNodeBEntity = Mapper.gNodeBModelToEntity(gNodeB);
		Core5GEntity core5gEntity = new Core5GEntity();
		core5gEntity.setId(core_id);
		gNodeBEntity.setCore5G(core5gEntity);
		gNodeBRepository.save(gNodeBEntity);
		if (gNodeB.getLocation() != null)
			locationService.saveGNodeBLocation(gNodeB.getGnbId(), gNodeB.getLocation());
	}

	public void deleteGNodeBs(List<Long> gNodeBIds) {
		gNodeBRepository.deleteByIdIn(gNodeBIds);
		
	}

}
