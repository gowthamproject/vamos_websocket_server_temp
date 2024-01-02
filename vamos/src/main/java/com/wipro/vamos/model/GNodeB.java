package com.wipro.vamos.model;

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

	private String gnbName;

	private String ipAddress;

	private long plmnId;

	private String status;

	private Location location;

}
