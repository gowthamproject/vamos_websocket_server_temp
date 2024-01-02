package com.wipro.vamos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
	
	private int enterpriseId;
	
	private String enterpriseName;

}
