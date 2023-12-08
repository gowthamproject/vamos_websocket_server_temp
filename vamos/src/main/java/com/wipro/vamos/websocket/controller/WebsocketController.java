package com.wipro.vamos.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.model.Alarm;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.model.Subscriber;
import com.wipro.vamos.model.Throughput;
import com.wipro.vamos.websocket.service.MessageService;

@RestController
@RequestMapping("/api/ws")
public class WebsocketController {
	
	@Autowired
	MessageService messageService;
	
	@PostMapping("throughput")
	public ResponseEntity<String> sendThroughput(@Payload String throughput){
		messageService.processAnsSendMessage( throughput);
		return ResponseEntity.ok("Throuhput Data Send to Client");
	}
	
	@PostMapping("alarm")
	public ResponseEntity<String> sendAlarm(@Payload String alarm){
		messageService.processAnsSendMessage( alarm);
		return ResponseEntity.ok("Alarm Data Send to Client");
	}
	
	@PostMapping("subscriber")
	public ResponseEntity<String> sendSubscriber(@Payload String subscriber){
		messageService.processAnsSendMessage( subscriber);
		return ResponseEntity.ok("Subscriber Data Send to Client");
	}
	
	@PostMapping("gnodeb")
	public ResponseEntity<String> sendGnodeb(@Payload String gNodeB){
		messageService.processAnsSendMessage( gNodeB);
		return ResponseEntity.ok("GNodeB Data Send to Client");
	}
	
	
	@MessageMapping("/msg")
	@SendTo("/topic/message")
	@PostMapping("message")
	public String send(String message) throws Exception{
		return message;
	}
}
