package com.wipro.vamos.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.websocket.service.MessageService;

@RestController
@RequestMapping("/api/ws")
public class WebsocketController {

	@Autowired
	MessageService messageService;

	@PostMapping("throughput")
	public ResponseEntity<String> sendThroughput(@RequestParam(name = "node_id") String node_id) {
		messageService.processAndSendMessage_Throughput(node_id);
		return ResponseEntity.ok("Throuhput Data Send to Client");
	}

	@PostMapping("gnodeb")
	public ResponseEntity<String> sendGnodeb(@RequestParam(name = "node_id") String node_id) {
		messageService.processAndSendMessage_GNodeB(node_id);
		return ResponseEntity.ok("GNodeB Data Send to Client");
	}

	@PostMapping("alarm")
	public ResponseEntity<String> sendAlarm(@RequestParam String node_id) {
		messageService.processAndSendMessage_Alarm(node_id);
		return ResponseEntity.ok("Alarm Data Send to Client");
	}

	@PostMapping("subscriber")
	public ResponseEntity<String> sendSubscriber(@RequestParam String node_id) {
		messageService.processAndSendMessage_Subscriber(node_id);
		return ResponseEntity.ok("Subscriber Data Send to Client");
	}

	@PostMapping("pdusession")
	public ResponseEntity<String> sendPDUSession(@RequestParam String node_id) {
		messageService.processAndSendMessage_PDUSession(node_id);
		return ResponseEntity.ok("Subscriber Data Send to Client");
	}

	@MessageMapping("/msg")
	@SendTo("/topic/message")
	@PostMapping("message")
	public String send(String message) throws Exception {
		return message;
	}

}
