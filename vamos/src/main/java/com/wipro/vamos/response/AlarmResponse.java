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
public class AlarmResponse {

	Map<String, Map<String, Long>> alarmCountByStatusandSeverity;

}
