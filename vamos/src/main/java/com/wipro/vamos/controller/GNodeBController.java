package com.wipro.vamos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.entity.GNodeBEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.response.GNodeBResponse;
import com.wipro.vamos.service.GNodeBService;

@RestController
@RequestMapping("/api/v1")
public class GNodeBController {

	@Autowired
	GNodeBService gNodeBService;

	@GetMapping("/gNBs")
	public ResponseEntity<List<GNodeBEntity>> getAllGNodeB() throws ResourceNotFoundException {
		return ResponseEntity.ok(gNodeBService.getAllGnodeB());
	}

	@GetMapping("/gNB/{gNB_id}")
	public ResponseEntity<GNodeB> getAllGNodeBById(@PathVariable(value = "gNB_id") long gnb_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(gNodeBService.getGNodeBByID(gnb_id));
	}

	@GetMapping("/gNB/nodes/{core_id}")
	public ResponseEntity<List<GNodeB>> getAllGNodeBByNodeId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(gNodeBService.getGNodeBByCoreID(core_id));
	}

	@GetMapping("/gNB/status/count/{core_id}")
	public ResponseEntity<GNodeBResponse> getGNodeBStatusCountByNodeId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(gNodeBService.getGNodeBStatusCountByCoreId(core_id));
	}

	@PostMapping("/gNB/saveGNodeB")
	public ResponseEntity<String> saveGNodeB(@RequestParam(name = "core_id") String core_id,
			@RequestBody GNodeB gNodeB) {
		gNodeBService.saveGNodeB(core_id, gNodeB);
		return ResponseEntity.ok().body("GNodeB Added.!");
	}

	@PostMapping("/gNB/saveGNodeBs")
	public ResponseEntity<String> saveGNodeBs(@RequestParam(name = "core_id") String core_id,
			@RequestBody List<GNodeB> gNodeBList) {
		for (GNodeB gNodeB : gNodeBList)
			saveGNodeB(core_id, gNodeB);
		return ResponseEntity.ok().body("GNodeB Added.!");
	}

	@GetMapping("/gNB/deleteGNodeBs")
	public ResponseEntity<String> deleteGNodeBs(@RequestParam("gNB_ids") String integersParam) {
		List<Long> gNodeBIds = Arrays.stream(integersParam.split(",")).map(Long::parseLong)
				.collect(Collectors.toList());
		gNodeBService.deleteGNodeBs(gNodeBIds);
		return ResponseEntity.ok().body("GNodeBs are deleted.!");
	}

}
