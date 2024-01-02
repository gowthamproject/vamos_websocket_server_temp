package com.wipro.vamos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CPE {

	private long cpeId;

	private String cpeName;

	private String ipAddress;

	private String status;

}
