package com.wipro.vamos.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.wipro.vamos.model.PDUSession;
import com.wipro.vamos.model.Throughput;
import com.wipro.vamos.response.AlarmCount;
import com.wipro.vamos.response.GNodeBCount;
import com.wipro.vamos.response.SubscriberResponse;
import com.wipro.vamos.service.AlarmService;
import com.wipro.vamos.service.GNodeBService;
import com.wipro.vamos.service.PDUSessionService;
import com.wipro.vamos.service.SubscriberService;
import com.wipro.vamos.service.ThrouhputService;

@Service
public class MessageService {

	@Autowired
	GNodeBService gNodeBService;
	@Autowired
	ThrouhputService throuhputService;
	@Autowired
	SubscriberService subscriberService;
	@Autowired
	AlarmService alarmService;
	@Autowired
	PDUSessionService pduSessionService;

	@Autowired
	public SimpMessageSendingOperations messagingTemplate;

	public void processAndSendMessage_GNodeB(String core_id) {
		GNodeBCount gNodeBResponse = gNodeBService.getGNodeBStatusCountByCoreId(core_id);
		System.out.println("Sending message to client -- /topic/gnodeb : " + gNodeBResponse);
		messagingTemplate.convertAndSend("/topic/gnodeb", gNodeBResponse);
	}

	public void processAndSendMessage_Throughput(String core_id) {
		Throughput throughputResponse = throuhputService.getThrouhputByCoreId(core_id);
		System.out.println("Sending message to client -- /topic/throuhput : " + throughputResponse.toString());
		messagingTemplate.convertAndSend("/topic/throuhput", throughputResponse);
	}

	public void processAndSendMessage_Subscriber(String core_id) {
		SubscriberResponse subscriberResponse = subscriberService.getSubscriberStatusCountByCoreId(core_id);
		System.out.println("Sending message to client -- /topic/subscriber : " + subscriberResponse.toString());
		messagingTemplate.convertAndSend("/topic/subscriber", subscriberResponse);
	}

	public void processAndSendMessage_PDUSession(String core_id) {
		PDUSession throughputResponse = pduSessionService.getPDUSessionStatusByCoreId(core_id);
		System.out.println("Sending message to client -- /topic/pdusession : " + throughputResponse.toString());
		messagingTemplate.convertAndSend("/topic/pdusession", throughputResponse);
	}

	public void processAndSendMessage_Alarm(String core_id) {
		AlarmCount alarmResponse = alarmService.getAlarmCountByCoreID(core_id);
		System.out.println("Sending message to client -- /topic/alarm : " + alarmResponse.toString());
		messagingTemplate.convertAndSend("/topic/alarm", alarmResponse);
	}

}