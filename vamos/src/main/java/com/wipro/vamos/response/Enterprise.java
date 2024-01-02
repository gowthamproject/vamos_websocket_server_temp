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
public class Enterprise {

	private List<P5gNetworkEnterprise> p5gNetworkEnterprises;
	

}
