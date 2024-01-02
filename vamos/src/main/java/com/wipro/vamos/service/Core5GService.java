package com.wipro.vamos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.SiteEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Core5G;
import com.wipro.vamos.repository.Core5GRepository;

@Service
public class Core5GService {

	@Autowired
	Core5GRepository core5gRepository;

	public Core5G getCore5GById(String core_id) throws ResourceNotFoundException {
		Optional<Core5GEntity> core5GOptional = core5gRepository.findById(core_id);
		if (!core5GOptional.isPresent())
			throw new ResourceNotFoundException("Core5G not found for this id :: " + core_id);

		return Mapper.core5gEntityToModel(core5GOptional.get());
	}

	public List<Core5G> getCore5GBySiteId(int site_id) throws ResourceNotFoundException {
		List<Core5GEntity> core5gList = core5gRepository.findBySiteId(site_id);
		if (core5gList == null || core5gList.isEmpty())
			throw new ResourceNotFoundException("Core5G not found for this site id :: " + site_id);
		return Mapper.core5gEntityToModelList(core5gList);
	}

	public void saveCore5G(int site_id, Core5G core5g) {
		Core5GEntity core5gEntity = Mapper.core5gModelToEntity(core5g);
		SiteEntity siteEntity = new SiteEntity();
		siteEntity.setId(site_id);
		core5gEntity.setSite(siteEntity);
		core5gRepository.save(core5gEntity);
	}

}
