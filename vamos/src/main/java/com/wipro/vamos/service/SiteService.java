package com.wipro.vamos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.SiteEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Location;
import com.wipro.vamos.model.Site;
import com.wipro.vamos.repository.SiteRepository;

@Service
public class SiteService {

	@Autowired
	SiteRepository siteRepository;
	@Autowired
	LocationService locationService;

	public Site getSiteById(int site_id) throws ResourceNotFoundException {
		Optional<SiteEntity> siteOptional = siteRepository.findById(site_id);
		if (!siteOptional.isPresent())
			throw new ResourceNotFoundException("Site not found for this id :: " + site_id);

		Site site = Mapper.siteEntityToModel(siteOptional.get());
		Location location = locationService.getLocationBySiteID(site_id);
		if (location != null) {
			site.setLocation(location);
		}
		return site;
	}

	public void saveSite(Site site) {
		SiteEntity siteEntity = Mapper.siteModelToEntity(site);
		siteRepository.save(siteEntity);
		if (site.getLocation() != null)
			locationService.saveSiteLocation(site.getSiteId(), site.getLocation());
	}

	public List<Site> getAllSite() throws ResourceNotFoundException {
		List<Site> siteList = Mapper.siteEntityToModelList(siteRepository.findAll());
		Location location = null;
		for (Site site : siteList) {
			location = locationService.getLocationBySiteID(site.getSiteId());
			if (location != null) {
				site.setLocation(location);
			}
		}
		return siteList;
	}

}
