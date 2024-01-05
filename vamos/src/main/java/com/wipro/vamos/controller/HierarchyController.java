package com.wipro.vamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.response.HierarchyResponse;
import com.wipro.vamos.service.HierarchyService;

@RestController
@RequestMapping("/api/v1")
public class HierarchyController {

	@Autowired
	HierarchyService hierarchyService;

	@GetMapping(value = "/hierarchy/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public HierarchyResponse getAllHierarchy() throws ResourceNotFoundException {
		return hierarchyService.getAllHierarchy();
	}

}
