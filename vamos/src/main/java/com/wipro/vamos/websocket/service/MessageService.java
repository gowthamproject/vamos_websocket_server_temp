package com.wipro.vamos.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    public void processAnsSendMessage( String alarm ) {

        messagingTemplate.convertAndSend( "/topic/greetings", alarm );
    }
}