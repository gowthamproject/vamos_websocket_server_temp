package com.wipro.vamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Site;
import com.wipro.vamos.service.SiteService;

@RestController
@RequestMapping("/api/v1")
public class SiteController {

	@Autowired
	SiteService siteService;

	@GetMapping(value = "/site/{site_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Site getSiteById(@PathVariable(name = "site_id") int site_id) throws ResourceNotFoundException {
		return siteService.getSiteById(site_id);
	}

	@GetMapping(value = "/sites", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Site> getAllSites() throws ResourceNotFoundException {
		return siteService.getAllSite();
	}

	@PostMapping("/saveSite")
	public String saveSite(@RequestBody Site site) {
		siteService.saveSite(site);
		return "Site Added.!";
	}

}
