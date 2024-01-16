package com.wipro.vamos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import com.wipro.vamos.model.Subscriber;
import com.wipro.vamos.response.SubscriberResponse;
import com.wipro.vamos.service.SubscriberService;

@RestController
@RequestMapping("/api/v1")
public class SubscriberController {

	@Autowired
	private SubscriberService subscriberService;

	@GetMapping("/subscriber")
	public ResponseEntity<List<Subscriber>> getAllSubscriber() throws ResourceNotFoundException {
		return ResponseEntity.ok(subscriberService.getAllSubscriber());
	}

	@GetMapping("/subscriber/{subscriber_id}")
	public ResponseEntity<Subscriber> getAllSubscriberById(@PathVariable(value = "subscriber_id") long subscriber_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(subscriberService.getSubscriberByID(subscriber_id));
	}

	@GetMapping("/subscriber/nodes/{core_id}")
	public ResponseEntity<List<Subscriber>> getAllSubscriberByNodeId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(subscriberService.getSubscriberByCoreID(core_id));
	}

	@GetMapping("/subscriber/status/count/{core_id}")
	public SubscriberResponse getSubscriberStatusCountByNodeId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return subscriberService.getSubscriberStatusCountByCoreId(core_id);
	}

	@PostMapping("/subscriber/saveSubscriber")
	public ResponseEntity<String> saveSubscriber(@RequestParam("core_id") String core_id,
			@RequestBody Subscriber subscriber) {
		subscriberService.saveSubscriber(core_id, subscriber);
		return ResponseEntity.ok().body("Subscriber Added.!");
	}

	@PostMapping("/subscriber/saveSubscribers")
	public ResponseEntity<String> saveSubscriber(@RequestParam("core_id") String core_id,
			@RequestBody List<Subscriber> subscribers) {
		for (Subscriber subscriber : subscribers)
			subscriberService.saveSubscriber(core_id, subscriber);
		return ResponseEntity.ok().body("Subscriber Added.!");
	}

	@DeleteMapping("/subscriber/deleteSubscribers")
	public ResponseEntity<String> deleteSubscriber(@RequestParam("subscriber_ids") String subscriber_ids) {
		List<Long> subscriberIdList = Arrays.stream(subscriber_ids.split(",")).map(Long::parseLong)
				.collect(Collectors.toList());
		for (Long subscriber_id : subscriberIdList)
			subscriberService.deleteGNodeBs(subscriber_id);
		return ResponseEntity.ok().body("Subscriber Deleted.!");
	}

	@PutMapping("/subscriber/updateSubscriber")
	public ResponseEntity<String> updateSubscriber(@RequestParam(name = "core_id") String core_id,
			@RequestBody Subscriber gNodeB) {
		saveSubscriber(core_id, gNodeB);
		return ResponseEntity.ok().body("Subscriber Updated.!");
	}

}
