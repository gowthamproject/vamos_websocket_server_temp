package com.wipro.vamos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Constant;
import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.GNodeBEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.repository.GNodeBRepository;
import com.wipro.vamos.response.GNodeBCount;

@Service
public class GNodeBService {

	@Autowired
	private GNodeBRepository gNodeBRepository;

	@Autowired
	private LocationService locationService;

	@Autowired
	private CPEService cpeService;

	public List<GNodeB> getAllGnodeB() {
		return Mapper.gNodeBEntityToModelList(gNodeBRepository.findAll());
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

	public GNodeBCount getGNodeBStatusCountByCoreId(String core_id) {
		List<GNodeBEntity> gnbList = gNodeBRepository.findByCore5GId(core_id);
		Map<String, Long> gNodeBCountByStatusMap = gnbList.stream()
				.collect(Collectors.groupingBy(GNodeBEntity::getStatus, Collectors.counting()));
		if (gNodeBCountByStatusMap == null)
			gNodeBCountByStatusMap = new HashMap<String, Long>();

		if (gNodeBCountByStatusMap.get(Constant.CONNECTED) == null)
			gNodeBCountByStatusMap.put(Constant.CONNECTED, 0l);

		if (gNodeBCountByStatusMap.get(Constant.DISCONNECTED) == null)
			gNodeBCountByStatusMap.put(Constant.DISCONNECTED, 0l);

		GNodeBCount gNodeBResponse = new GNodeBCount();
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

	public void deleteGNodeBs(Long gNodeBId) {
		// locationService.deleteGNodeBLocation(gNodeBId);
		// cpeService.deleteCPEByGNodeB(gNodeBId);
		gNodeBRepository.deleteById(gNodeBId);
	}

	public long getGNodeBCount() {
		return gNodeBRepository.count();
	}

}
