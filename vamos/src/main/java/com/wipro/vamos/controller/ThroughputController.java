package com.wipro.vamos.controller;

import java.util.List;

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
import com.wipro.vamos.model.Throughput;
import com.wipro.vamos.service.ThrouhputService;

@RestController
@RequestMapping("/api/v1")
public class ThroughputController {

	@Autowired
	ThrouhputService throuhputService;

	@GetMapping(value = "/throuhput/nodes/{core_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Throughput> getThroughputsByCoreId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return throuhputService.getThrouhputsByCoreId(core_id);
	}

	@GetMapping(value = "/throuhput/current/nodes/{core_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Throughput getCurrentThroughputByCoreId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return throuhputService.getThrouhputByCoreId(core_id);
	}

	@PostMapping("/saveThroughput")
	public String saveThroughput(@RequestParam(name="core_id")String core_id, @RequestBody Throughput throughput) {
		throuhputService.saveThroughput(core_id, throughput);
		return "Throughput saved!!!";
	}
}
