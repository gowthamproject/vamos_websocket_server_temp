package com.wipro.vamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.PDUSession;
import com.wipro.vamos.service.PDUSessionService;

@RestController
@RequestMapping("/api/v1")
public class PDUSessionController {

	@Autowired
	PDUSessionService pduSessionService;

	@GetMapping(value = "/pduSession/nodes/{core_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public PDUSession getPDUSessionStatusByNodeId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return pduSessionService.getPDUSessionStatusByCoreId(core_id);
	}

	@PostMapping("/pduSession/savePDUSession")
	public ResponseEntity<String> savePduSession(@RequestParam("core_id") String core_id, @RequestBody PDUSession pduSession) {
		pduSessionService.savePduSession(core_id, pduSession);
		return ResponseEntity.ok().body("PDUSession Added.!");
	}
}
