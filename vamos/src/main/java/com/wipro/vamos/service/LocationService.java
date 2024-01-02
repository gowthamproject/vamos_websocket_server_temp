package com.wipro.vamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.GNodeBEntity;
import com.wipro.vamos.entity.LocationEntity;
import com.wipro.vamos.entity.SiteEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Location;
import com.wipro.vamos.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public List<Location> getAllLocation() {
		return Mapper.locationEntityToModelList(locationRepository.findAll());
	}

	public Location getLocationByGNodeBID(long gnb_id) throws ResourceNotFoundException {
		LocationEntity locationEntity = locationRepository.findByGNodeBId(gnb_id);
		/*
		 * if (locationEntity == null) throw new
		 * ResourceNotFoundException("location not found for this gnb_id :: " +
		 * gnb_id);
		 */
		Location location = null;
		if (locationEntity != null)
			location = Mapper.locationEntityToModel(locationEntity);
		return location;
	}

	public Location getLocationBySiteID(long site_id) throws ResourceNotFoundException {
		LocationEntity locationEntity = locationRepository.findBySiteId(site_id);
		/*
		 * if (locationEntity == null) throw new
		 * ResourceNotFoundException("location not found for this site_id :: " +
		 * site_id);
		 */
		Location location = null;
		if (locationEntity != null)
			location = Mapper.locationEntityToModel(locationEntity);
		return location;
	}

	public void saveSiteLocation(int site_id, Location location) {
		LocationEntity locationEntity = Mapper.locationModelToEntity(location);
		SiteEntity siteEntity = new SiteEntity();
		siteEntity.setId(site_id);
		locationEntity.setSite(siteEntity);
		locationRepository.save(locationEntity);
	}

	public void saveGNodeBLocation(long gnb_id, Location location) {
		LocationEntity locationEntity = Mapper.locationModelToEntity(location);
		GNodeBEntity gNodeBEntity = new GNodeBEntity();
		gNodeBEntity.setId(gnb_id);
		locationEntity.setGNodeB(gNodeBEntity);
		locationRepository.save(locationEntity);
	}

}
