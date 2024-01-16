package com.wipro.vamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Location;
import com.wipro.vamos.model.Site;
import com.wipro.vamos.service.LocationService;
import com.wipro.vamos.service.SiteService;

@RestController
@RequestMapping("/api/v1")
public class SiteController {

	@Autowired
	SiteService siteService;

	@Autowired
	LocationService locationService;

	@GetMapping(value = "/site/{site_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Site getSiteById(@PathVariable(name = "site_id") int site_id) throws ResourceNotFoundException {
		return siteService.getSiteById(site_id);
	}

	@GetMapping(value = "/sites", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Site> getAllSites() throws ResourceNotFoundException {
		return siteService.getAllSite();
	}

	@GetMapping(value = "/site/{enterprise_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Site> getSiteByEnterpriseId(@PathVariable(name = "enterprise_id") int enterprise_id)
			throws ResourceNotFoundException {
		return siteService.getSiteByEnterpriseId(enterprise_id);
	}

	@GetMapping(value = "/site/count", produces = { MediaType.APPLICATION_JSON_VALUE })
	public long getSitecount() throws ResourceNotFoundException {
		return siteService.getSitecount();
	}

	@PostMapping("/site/saveSite")
	public String saveSite(@RequestParam(name = "enterprise_id") int enterprise_id, @RequestBody Site site) {
		siteService.saveSite(enterprise_id, site);
		return "Site Added.!";
	}

	@PutMapping("/location/udpateLocation")
	public ResponseEntity<String> updateLocationByGNodeBId(@RequestParam(name = "site_id") int site_id,
			@RequestBody Location location) {
		locationService.saveSiteLocation(site_id, location);
		return ResponseEntity.ok().body("Site Location Updated.!");
	}

}
