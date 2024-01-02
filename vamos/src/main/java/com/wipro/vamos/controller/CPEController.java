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
import com.wipro.vamos.model.CPE;
import com.wipro.vamos.response.CPEResponse;
import com.wipro.vamos.service.CPEService;

@RestController
@RequestMapping("/api/v1")
public class CPEController {

	@Autowired
	CPEService cpeService;

	@GetMapping("/cpes")
	public ResponseEntity<List<CPE>> getAllCPE() throws ResourceNotFoundException {
		return ResponseEntity.ok(cpeService.getAllCPE());
	}

	@GetMapping("/cpe/{cpe_id}")
	public ResponseEntity<CPE> getCPEById(@PathVariable(value = "gnb_id") long gnb_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(cpeService.getCPEByID(gnb_id));
	}

	@GetMapping("/cpe/gnode/{gnb_id}")
	public ResponseEntity<List<CPE>> getAllGNodeBByNodeId(@PathVariable(value = "gnb_id") long gnb_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(cpeService.getCPEByGNodeBID(gnb_id));
	}

	@GetMapping("/cpe/status/count/{gnb_id}")
	public ResponseEntity<CPEResponse> getGNodeBStatusCountByNodeId(@PathVariable(value = "gnb_id") long gnb_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(cpeService.getCPEStatusCountByGNodeBID(gnb_id));
	}

	@PostMapping("/saveCPE")
	public ResponseEntity<String> saveSite(@RequestParam(name="gnb_id")int gnb_id, @RequestBody CPE cpe) {
		cpeService.saveCPE(gnb_id, cpe);
		return ResponseEntity.ok().body("CPE Added.!");
	}

}
