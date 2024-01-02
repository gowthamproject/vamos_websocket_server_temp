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
public class P5gNetworkEnterprise {
	
	private String id;
	
	private String name;
	
	private List<Site> sites;
	
	
	
}
