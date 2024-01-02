package com.wipro.vamos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Core5G {
	
	private String id;
	
	private String name;
	
	private String status;
	
	private String endpoint;
	
	private String apiKey;
	
	private List<GNodeB> gNodeBs;

}
