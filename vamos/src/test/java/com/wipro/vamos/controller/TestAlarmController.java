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

import com.wipro.vamos.model.Alarm;
import com.wipro.vamos.repository.AlarmRepository;

@WebMvcTest(AlarmController.class)
public class TestAlarmController {

	@MockBean
	private AlarmRepository alarmRepository;

	@Autowired
	private MockMvc mockMvc;

	private static List<Alarm> alarms = null;

	@BeforeAll
	private static void loadTestData() {
		alarms = new ArrayList<>(Arrays.asList(
				new Alarm(9, "5GCore", "Raemis 5G Core", "2023-09-22 10:30:41.000", "Critical", "Open",
						"TCPCONNECTIONFAILURE", "TCPCONNECTIONFAILURE",
						"CNCOREEVENTS-ALERT-CNCOREEVENTS--hqbeta_cluster", "Yes", "1045173342497102197"),
				new Alarm(5, "5GCore", "Raemis 5G Core", "2023-09-22 10:30:41.000", "Major", "Open",
						"TCPCONNECTIONFAILURE", "TCPCONNECTIONFAILURE",
						"CNCOREEVENTS-ALERT-CNCOREEVENTS--hqbeta_cluster", "Yes", "1045173342497102197"),
				new Alarm(3, "5GCore", "Raemis 5G Core", "2023-09-22 10:30:41.000", "Warning", "Closed",
						"TCPCONNECTIONFAILURE", "TCPCONNECTIONFAILURE",
						"CNCOREEVENTS-ALERT-CNCOREEVENTS--hqbeta_cluster", "Yes", "1045173342497102197")));
	}

	@Test
	public void getAlarmByNodeId() throws Exception {
		long alarm_id = 9l;
		when(alarmRepository.findById(alarm_id)).thenReturn(Optional.of(alarms.get(0)));
		mockMvc.perform(get("/api/v1/alarm/{alarm_id}", alarm_id)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.alarmId").value(alarms.get(0).getAlarmId()))
				.andExpect(jsonPath("$.nodeId").value(alarms.get(0).getNodeId()))
				.andExpect(jsonPath("$.nodeName").value(alarms.get(0).getNodeName()))
				.andExpect(jsonPath("$.nodeType").value(alarms.get(0).getNodeType()))
				.andExpect(jsonPath("$.updatedTime").value(alarms.get(0).getUpdatedTime()))
				.andExpect(jsonPath("$.severity").value(alarms.get(0).getSeverity()))
				.andExpect(jsonPath("$.status").value(alarms.get(0).getStatus()))
				.andExpect(jsonPath("$.eventType").value(alarms.get(0).getEventType()))
				.andExpect(jsonPath("$.probableCause").value(alarms.get(0).getProbableCause()))
				.andExpect(jsonPath("$.additionalMessage").value(alarms.get(0).getAdditionalMessage()))
				.andExpect(jsonPath("$.acknowledged").value(alarms.get(0).getAcknowledged())).andDo(print());
	}

	@Test
	public void getAlarmByNodeIdExceptionHandler() throws Exception {
		long alarm_id = 7879l;
		String errorMsg = "Alarm not found for this id :: " + alarm_id;
		when(alarmRepository.findById(alarm_id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/v1/alarm/{alarm_id}", alarm_id)).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.message").value(errorMsg)).andDo(print());
	}

	@Test
	void getAllAlarmByNodeId() throws Exception {
		String node_id = "1045173342497102197";
		when(alarmRepository.findAllByNodeId(node_id)).thenReturn(alarms);
		mockMvc.perform(get("/api/v1/alarm/nodes/{node_id}", node_id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(alarms.size())).andDo(print());
	}

}