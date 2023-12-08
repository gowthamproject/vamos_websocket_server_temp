package com.wipro.vamos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.repository.PDUSessionRepository;

@RestController
@RequestMapping("/api/v1")
public class PDUSessionController {

	@Autowired
	PDUSessionRepository pduSessionRepository;

	@GetMapping(value = "/pdusession/nodes/{node_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Integer getPDUSessionStatusByNodeId(@PathVariable(value = "node_id") String node_id)
			throws ResourceNotFoundException {
		return pduSessionRepository.findAllByNodeId(node_id).size();
	}

}
