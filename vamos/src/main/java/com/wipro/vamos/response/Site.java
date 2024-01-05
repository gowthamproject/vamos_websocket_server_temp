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
public class Site {

	private int siteId;

	private String siteName;

	private Location location;
	
	private String peekAlarmSeverity;
	
	private List<Core5G> core5gs;

}
