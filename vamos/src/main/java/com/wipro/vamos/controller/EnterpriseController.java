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
import com.wipro.vamos.model.Enterprise;
import com.wipro.vamos.service.EnterpriseService;

@RestController
@RequestMapping("/api/v1")
public class EnterpriseController {

	@Autowired
	EnterpriseService enterpriseService;
	
	@GetMapping(value = "/enterprises", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Enterprise> getAllEnterprise() throws ResourceNotFoundException {
		return enterpriseService.getAllEnterprise();
	}

	@GetMapping(value = "/enterprise/{ent_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Enterprise getEnterpriseById(@PathVariable(name = "ent_id") int ent_id) throws ResourceNotFoundException {
		return enterpriseService.getEnterpriseById(ent_id);
	}

	@PostMapping("/saveEnterprise")
	public String saveEnterprise(@RequestBody Enterprise enterprise) {
		enterpriseService.saveEnterprise(enterprise);
		return "Enterprise saved!!!";
	}

}
