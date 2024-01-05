package com.wipro.vamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.wipro.vamos.model.Location;
import com.wipro.vamos.service.LocationService;

@RestController
@RequestMapping("/api/v1")
public class LocationController {

	@Autowired
	LocationService locationService;

	@GetMapping("/locations")
	public ResponseEntity<List<Location>> getAllCPE() throws ResourceNotFoundException {
		return ResponseEntity.ok(locationService.getAllLocation());
	}

	@GetMapping("/location/gnodeb/{gnb_id}")
	public ResponseEntity<Location> getLocationByGNodeBID(@PathVariable(value = "gnb_id") long gnb_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(locationService.getLocationByGNodeBID(gnb_id));
	}

	@GetMapping("/location/site/{site_id}")
	public ResponseEntity<Location> getLocationBySiteID(@PathVariable(value = "site_id") long site_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(locationService.getLocationBySiteID(site_id));
	}

	@PostMapping("/location/saveSiteLocation")
	public ResponseEntity<String> saveSiteLocation(@RequestParam(name = "site_id") int site_id,
			@RequestBody Location location) {
		locationService.saveSiteLocation(site_id, location);
		return ResponseEntity.ok().body("Site Location Added.!");
	}

	@PostMapping("/location/saveGNodeBLocation")
	public ResponseEntity<String> saveGNodeBLocation(@RequestParam(name = "gnb_id") long gnb_id,
			@RequestBody Location location) {
		locationService.saveGNodeBLocation(gnb_id, location);
		return ResponseEntity.ok().body("GNodeB Location Added.!");
	}

	@PutMapping("/location/udpateSiteLocation")
	public ResponseEntity<String> updateLocationBySiteId(@RequestParam(name = "site_id") int site_id,
			@RequestBody Location location) {
		locationService.saveSiteLocation(site_id, location);
		return ResponseEntity.ok().body("Site Location Updated.!");
	}

	@PutMapping("/location/udpateGNodeBLocation")
	public ResponseEntity<String> updateLocationByGNodeBId(@RequestParam(name = "gnb_id") int gnb_id,
			@RequestBody Location location) {
		locationService.saveSiteLocation(gnb_id, location);
		return ResponseEntity.ok().body("Site Location Updated.!");
	}

	@DeleteMapping("/location/site/deleteLocation")
	public ResponseEntity<String> deleteSiteLocation(@RequestParam("site_ids") int site_id) {
		locationService.deleteSiteLocation(site_id);
		return ResponseEntity.ok().body("Site's Location is deleted.!");
	}

	@DeleteMapping("/location/gnodeb/deleteLocation")
	public ResponseEntity<String> deleteGNodeBLocation(@RequestParam("gnb_id") long gnb_id) {
		locationService.deleteGNodeBLocation(gnb_id);
		return ResponseEntity.ok().body("GNodeB's Location is deleted.!");
	}

	@GetMapping("/location/count")
	public ResponseEntity<Long> getLocationCount() {
		return ResponseEntity.ok().body(locationService.getLocationCount());
	}
}
