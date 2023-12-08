package com.wipro.vamos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThroughputResponse {

	private String update_time;

	private long uplink;

	private long downlink;

}
