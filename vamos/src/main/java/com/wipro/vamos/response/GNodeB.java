package com.wipro.vamos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GNodeB {

	private long gnbId;

	private String name;
	
	private int plmnId;
	
	private String ipAddress;
	
	private String status;
	
	private Location location;
}
