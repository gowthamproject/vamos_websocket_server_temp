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

	private String coreId;

	private String name;

	private String discoveryStatus;

	private String endpoint;

	private String apikey;
	
	private String peekAlarmSeverity;
	
	private AlarmCount alarmCount;

	private List<GNodeB> gNodeBs;

}
