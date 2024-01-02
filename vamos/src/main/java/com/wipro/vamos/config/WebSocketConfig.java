package com.wipro.vamos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Enable and configure Stomp over WebSocket.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * Register Stomp endpoints: the url to open the WebSocket connection.
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		// Register the "/ws" endpoint, enabling the SockJS protocol.
		// SockJS is used (both client and server side) to allow alternative
		registry.addEndpoint("/vamosws").setAllowedOrigins("*");
		registry.addEndpoint("/vamosws").setAllowedOrigins("*").withSockJS();

	}

	/**
	 * Configure the message broker.
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

}