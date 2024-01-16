package com.wipro.vamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Core5G;
import com.wipro.vamos.service.Core5GService;

@RestController
@RequestMapping("/api/v1")
public class Core5GController {

	@Autowired
	Core5GService core5gService;

	@GetMapping(value = "/core5g/{core_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Core5G getCore5GById(@PathVariable(name = "core_id") String core_id) throws ResourceNotFoundException {
		return core5gService.getCore5GById(core_id);
	}

	@GetMapping(value = "/core5g/count", produces = { MediaType.APPLICATION_JSON_VALUE })
	public long getCore5GCount() throws ResourceNotFoundException {
		return core5gService.getCore5GCount();
	}

	@PostMapping("/savecore5g")
	public String saveSite(@RequestParam(name = "site_id") int site_id, @RequestBody Core5G core5g) {
		core5gService.saveCore5G(site_id, core5g);
		return "Core5G saved!!!";
	}

}