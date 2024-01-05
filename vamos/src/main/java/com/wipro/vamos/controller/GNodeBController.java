package com.wipro.vamos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.response.GNodeBCount;
import com.wipro.vamos.service.GNodeBService;

@RestController
@RequestMapping("/api/v1")
public class GNodeBController {

	@Autowired
	GNodeBService gNodeBService;

	@GetMapping(value = "/gNBs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<GNodeB>> getAllGNodeB() throws ResourceNotFoundException {
		return ResponseEntity.ok(gNodeBService.getAllGnodeB());
	}

	@GetMapping(value = "/gNB/{gNB_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GNodeB> getAllGNodeBById(@PathVariable(value = "gNB_id") long gnb_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(gNodeBService.getGNodeBByID(gnb_id));
	}

	@GetMapping(value = "/gNB/nodes/{core_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<GNodeB>> getAllGNodeBByNodeId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(gNodeBService.getGNodeBByCoreID(core_id));
	}

	@GetMapping("/gNB/status/count/{core_id}")
	public ResponseEntity<GNodeBCount> getGNodeBStatusCountByNodeId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(gNodeBService.getGNodeBStatusCountByCoreId(core_id));
	}

	@PostMapping("/gNB/saveGNodeB")
	public ResponseEntity<String> saveGNodeB(@RequestParam(name = "core_id") String core_id,
			@RequestBody GNodeB gNodeB) {
		gNodeBService.saveGNodeB(core_id, gNodeB);
		return ResponseEntity.ok().body("GNodeB Added/Updated.!");
	}

	@PostMapping("/gNB/saveGNodeBs")
	public ResponseEntity<String> saveGNodeBs(@RequestParam(name = "core_id") String core_id,
			@RequestBody List<GNodeB> gNodeBList) {
		for (GNodeB gNodeB : gNodeBList)
			saveGNodeB(core_id, gNodeB);
		return ResponseEntity.ok().body("GNodeB Added/Updated.!");
	}

	@PutMapping("/gNB/udpateGNodeB")
	public ResponseEntity<String> updateGNodeBs(@RequestParam(name = "core_id") String core_id,
			@RequestBody GNodeB gNodeB) {
		saveGNodeB(core_id, gNodeB);
		return ResponseEntity.ok().body("GNodeB Updated.!");
	}

	@DeleteMapping("/gNB/deleteGNodeBs")
	public ResponseEntity<String> deleteGNodeBs(@RequestParam("gNB_ids") String gNB_ids) {
		List<Long> gNodeBIds = Arrays.stream(gNB_ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
		for (Long gnb_id : gNodeBIds)
			gNodeBService.deleteGNodeBs(gnb_id);
		return ResponseEntity.ok().body("GNodeBs are deleted.!");
	}

	@GetMapping("/gNB/count")
	public ResponseEntity<Long> getGNodeBCount() {
		return ResponseEntity.ok().body(gNodeBService.getGNodeBCount());
	}

}
