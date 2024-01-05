package com.wipro.vamos.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CPECount {

	private long gnb_id;
	
	private Map<String, Long> cpeCountByStatusMap ;

}
