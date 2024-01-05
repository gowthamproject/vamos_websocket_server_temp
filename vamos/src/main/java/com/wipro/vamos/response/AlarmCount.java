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
public class AlarmCount {

	private String core_id;

	private long totalAlarmCount = 0;

	private long openedAlarmCount = 0;

	private long closedAlarmCount = 0;

	private Map<String, Map<String, Long>> alarmCountByStatusandSeverity = null;

}
