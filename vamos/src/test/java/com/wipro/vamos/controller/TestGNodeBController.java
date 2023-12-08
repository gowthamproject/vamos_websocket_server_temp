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

import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.repository.GNodeBRepository;

@WebMvcTest(GNodeBController.class)
public class TestGNodeBController {

	@MockBean
	private GNodeBRepository gNodeBRepository;

	@Autowired
	private MockMvc mockMvc;

	private static List<GNodeB> gNodeBs = null;

	@BeforeAll
	private static void loadTestData() {
		gNodeBs = new ArrayList<>(
				Arrays.asList(new GNodeB(111, "gnb-1", 0011, "192.168.22.10", "Conected", "1045173342497102197"),
						new GNodeB(222, "gnb-2", 7834, "192.168.22.20", "Disconnected", "1045173342497102197"),
						new GNodeB(333, "gnb-3", 2354, "192.168.22.30", "Conected", "1045173342497102197")));
	}

	@Test
	public void getAllGNodeB() throws Exception{
		 when(gNodeBRepository.findAll()).thenReturn(gNodeBs);
		    mockMvc.perform(get("/api/v1/gNB"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.size()").value(gNodeBs.size()))
		        .andDo(print());
	}

	@Test
	public void getAllGNodeBById() throws Exception {
		long gNB_id = 9l;
		when(gNodeBRepository.findById(gNB_id)).thenReturn(Optional.of(gNodeBs.get(0)));
		mockMvc.perform(get("/api/v1/gNB/{gNB_id} ", gNB_id)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.gnbId").value(gNodeBs.get(0).getGnbId()))
				.andExpect(jsonPath("$.gnbName").value(gNodeBs.get(0).getGnbName()))
				.andExpect(jsonPath("$.plmnId").value(gNodeBs.get(0).getPlmnId()))
				.andExpect(jsonPath("$.ipAddress").value(gNodeBs.get(0).getIpAddress()))
				.andExpect(jsonPath("$.status").value(gNodeBs.get(0).getStatus()))
				.andExpect(jsonPath("$.nodeId").value(gNodeBs.get(0).getNodeId())).andDo(print());
	}

	@Test
	public void GetAllGNodeBByIdExceptionHandler() throws Exception {
		long gNB_id = 1234l;
		String errorMsg = "gNB not found for this id :: " + gNB_id;
		when(gNodeBRepository.findById(gNB_id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/v1/gNB/{gNB_id} ", gNB_id)).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.message").value(errorMsg)).andDo(print());
	}

	@Test
	public void getAllGNodeBByNodeId() throws Exception {
		String node_id = "1045173342497102197";
		when(gNodeBRepository.findAllByNodeId(node_id)).thenReturn(gNodeBs);
		mockMvc.perform(get("/api/v1/gNB/nodes/{node_id}", node_id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(gNodeBs.size())).andDo(print());
	}

	@Test
	public void getGNodeBStatusCountByNodeId() throws Exception {
		String node_id = "1045173342497102197";
		when(gNodeBRepository.findAllByNodeId(node_id)).thenReturn(gNodeBs);
	}

}
