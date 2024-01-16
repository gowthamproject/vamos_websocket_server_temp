package com.wipro.vamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.SystemInformation;
import com.wipro.vamos.service.SystemInformationService;

@RestController
@RequestMapping("/api/v1")
public class SystemInfoController {
	
	@Autowired
	SystemInformationService systemInformationService;

	
	@GetMapping("/systemInformation/{core_id}")
	public ResponseEntity<List<SystemInformation>> getSystemInformationByCoreId(
			@PathVariable(value = "core_id") String core_id) throws ResourceNotFoundException {
		return ResponseEntity.ok().body(systemInformationService.getSystemInformationByCoreId(core_id));
	}
	
	@PostMapping("/systemInformation/saveSystemInfos")
	public ResponseEntity<String> saveSystemInformations(@RequestParam("core_id") String core_id, @RequestBody List<SystemInformation> systemInformations){
		for(SystemInformation systemInformation : systemInformations) {
		systemInformationService.saveInformation(core_id, systemInformation);
		}
		return ResponseEntity.ok().body("SystemInformation Saved.!");
	}
}
