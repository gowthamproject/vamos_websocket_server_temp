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
import com.wipro.vamos.model.Subscriber;
import com.wipro.vamos.repository.SubscriberRepository;

@RestController
@RequestMapping("/api/v1")
public class SubscriberController {

	@Autowired
	private SubscriberRepository subscriberRepository;

	@GetMapping("/subscriber")
	public ResponseEntity<List<Subscriber>> getAllSubscriber() throws ResourceNotFoundException {
		return ResponseEntity.ok(subscriberRepository.findAll());
	}

	@GetMapping("/subscriber/{subscriber_id}")
	public ResponseEntity<Subscriber> getAllSubscriberById(@PathVariable(value = "subscriber_id") long subscriber_id)
			throws ResourceNotFoundException {
		Subscriber subscriber = subscriberRepository.findById(subscriber_id).orElseThrow(
				() -> new ResourceNotFoundException("Subscriber not found for this id :: " + subscriber_id));
		return ResponseEntity.ok().body(subscriber);
	}

	@GetMapping("/subscriber/nodes/{node_id}")
	public ResponseEntity<List<Subscriber>> getAllSubscriberByNodeId(@PathVariable(value = "node_id") String node_id)
			throws ResourceNotFoundException {
		List<Subscriber> subscriberList = subscriberRepository.findAllByNodeId(node_id);
		return ResponseEntity.ok().body(subscriberList);
	}

	@GetMapping("/subscriber/status/count/{node_id}")
	public Map<String, Long> getSubscriberStatusCountByNodeId(@PathVariable(value = "node_id") String node_id)
			throws ResourceNotFoundException {
		List<Subscriber> subscriberList = subscriberRepository.findAllByNodeId(node_id);
		Map<String, Long> readers = subscriberList.stream()
				.collect(Collectors.groupingBy(Subscriber::getStatus, Collectors.counting()));
		return readers;
	}

}
