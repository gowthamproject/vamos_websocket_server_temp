package com.wipro.vamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Throughput;
import com.wipro.vamos.repository.ThroughputRepository;
import com.wipro.vamos.response.ThroughputResponse;

@RestController
@RequestMapping("/api/v1")
public class ThroughputController {

	@Autowired
	ThroughputRepository throughputRepository;

	@GetMapping(value = "/throuhput/nodes/{node_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ThroughputResponse getThroughputByNodeId(@PathVariable(value = "node_id") String node_id)
			throws ResourceNotFoundException {
		List<Throughput> troughputs = throughputRepository.findAllByNodeId(node_id);
		ThroughputResponse response = new ThroughputResponse();
		response.setUplink(troughputs.get(0).getUplink());
		response.setDownlink(troughputs.get(0).getDownlink());
		response.setUpdate_time(troughputs.get(0).getUpdated_time());

		return response;
	}
}
