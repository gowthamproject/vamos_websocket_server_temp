package com.wipro.vamos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
	
	private String latitude;
	
	private String longitude;
	
	private String region_state;

}
