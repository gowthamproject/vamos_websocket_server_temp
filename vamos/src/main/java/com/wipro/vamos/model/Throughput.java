package com.wipro.vamos.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Throughput {

	private long id;

	@Builder.Default
	private long uplink = 0;

	@Builder.Default
	private long downlink = 0;

	private Date updatedTime;

}
