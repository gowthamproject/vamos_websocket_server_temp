package com.wipro.vamos.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.repository.GNodeBRepository;

@RestController
@RequestMapping("/api/v1")
public class GNodeBController {

	@Autowired
	private GNodeBRepository gNodeBRepository;

	@GetMapping("/gNB")
	public ResponseEntity<List<GNodeB>> getAllGNodeB() throws ResourceNotFoundException {
		return ResponseEntity.ok(gNodeBRepository.findAll());
	}

	@GetMapping("/gNB/{gNB_id}")
	public ResponseEntity<GNodeB> getAllGNodeBById(@PathVariable(value = "gNB_id") long gnb_id)
			throws ResourceNotFoundException {
		GNodeB gNB = gNodeBRepository.findById(gnb_id)
				.orElseThrow(() -> new ResourceNotFoundException("gNB not found for this id :: " + gnb_id));
		return ResponseEntity.ok().body(gNB);
	}

	@GetMapping("/gNB/nodes/{node_id}")
	public ResponseEntity<List<GNodeB>> getAllGNodeBByNodeId(@PathVariable(value = "node_id") String node_id)
			throws ResourceNotFoundException {
		List<GNodeB> gNBList = gNodeBRepository.findAllByNodeId(node_id);
		return ResponseEntity.ok().body(gNBList);
	}

	@GetMapping("/gNB/status/count/{node_id}")
	public Map<String, Long> getGNodeBStatusCountByNodeId(@PathVariable(value = "node_id") String node_id)
			throws ResourceNotFoundException {
		List<GNodeB> gnbList = gNodeBRepository.findAllByNodeId(node_id);
		Map<String, Long> readers = gnbList.stream()
				.collect(Collectors.groupingBy(GNodeB::getStatus, Collectors.counting()));
		return readers;
	}

}
