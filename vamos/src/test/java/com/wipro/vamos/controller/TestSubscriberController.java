package com.wipro.vamos.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.wipro.vamos.model.Subscriber;
import com.wipro.vamos.repository.SubscriberRepository;

@WebMvcTest(SubscriberController.class)
public class TestSubscriberController {

	@MockBean
	private SubscriberRepository subscriberRepository;

	@Autowired
	private MockMvc mockMvc;

	private static List<Subscriber> subscribers = null;

	@BeforeAll
	private static void loadTestData() {
		subscribers = new ArrayList<>(Arrays.asList(
				new Subscriber(5656, "Subscriber-1", 315010002313657l, 356082110002134l, "192.168.10.21", "Attached",
						"230451733424971555"),
				new Subscriber(7676, "Subscriber-2", 315010002313657l, 356082110002134l, "192.168.10.22", "Attached",
						"230451733424971555"),
				new Subscriber(9686, "Subscriber-3", 315010002313657l, 356082110002134l, "192.168.10.23", "Attached",
						"230451733424971555")));
	}

	@Test
	public void getAllSubscriber() throws Exception{
		 when(subscriberRepository.findAll()).thenReturn(subscribers);
		    mockMvc.perform(get("/api/v1/subscriber"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.size()").value(subscribers.size()))
		        .andDo(print());
	}

	@Test
	public void getAllSubscriberById() throws Exception {
		long subscriber_id = 9686;
		when(subscriberRepository.findById(subscriber_id)).thenReturn(Optional.of(subscribers.get(0)));
		mockMvc.perform(get("/api/v1/subscriber/{subscriber_id} ", subscriber_id)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(subscribers.get(0).getId()))
				.andExpect(jsonPath("$.name").value(subscribers.get(0).getName()))
				.andExpect(jsonPath("$.imsi").value(subscribers.get(0).getImsi()))
				.andExpect(jsonPath("$.imei").value(subscribers.get(0).getImei()))
				.andExpect(jsonPath("$.ipAddress").value(subscribers.get(0).getIpAddress()))
				.andExpect(jsonPath("$.status").value(subscribers.get(0).getStatus()))
				.andExpect(jsonPath("$.nodeId").value(subscribers.get(0).getNodeId())).andDo(print());
	}

	@Test
	public void getAllSubscriberByIdExceptionHandler() throws Exception {
		long subscriber_id = 9686;
		String errorMsg = "Subscriber not found for this id :: " + subscriber_id;
		when(subscriberRepository.findById(subscriber_id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/v1/subscriber/{subscriber_id} ", subscriber_id)).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.message").value(errorMsg)).andDo(print());
	}

	@Test
	public void getAllSubscriberByNodeId() throws Exception {
		String node_id = "230451733424971555";
		when(subscriberRepository.findAllByNodeId(node_id)).thenReturn(subscribers);
		mockMvc.perform(get("/api/v1/subscriber/nodes/{node_id}", node_id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(subscribers.size())).andDo(print());
	}

	@Test
	public void getGNodeBStatusCountByNodeId() throws Exception {
		String node_id = "230451733424971555";
		when(subscriberRepository.findAllByNodeId(node_id)).thenReturn(subscribers);
	}

}
