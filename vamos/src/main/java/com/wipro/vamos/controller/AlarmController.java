package com.wipro.vamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Alarm;
import com.wipro.vamos.response.AlarmCount;
import com.wipro.vamos.service.AlarmService;

@RestController
@RequestMapping("/api/v1")
public class AlarmController {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(AlarmController.class);

	@Autowired
	AlarmService alarmService;

	@GetMapping(value = "/alarm/{alarm_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Alarm> getAlarmById(@PathVariable(value = "alarm_id") long alarm_id)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(alarmService.getAlarmByID(alarm_id));
	}

	@GetMapping(value = "/alarm/nodes/{core_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Alarm> getAllAlarmByCoreId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return alarmService.getAlarmByCoreID(core_id);
	}

	@GetMapping(value = "/alarm/count/{core_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public AlarmCount getAlarmCountByCoreId(@PathVariable(value = "core_id") String core_id)
			throws ResourceNotFoundException {
		return alarmService.getAlarmCountByCoreID(core_id);

	}

	@GetMapping(value = "/alarm/peekseverity/site/{site_id}/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPeekAlarmSeverityBySiteId(@PathVariable(name = "site_id") int site_id)
			throws ResourceNotFoundException {
		return alarmService.getPeekAlarmSeverityBySiteId(site_id);
	}

	@GetMapping(value = "/alarm/peekseverity/node/{core_id}/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getPeekAlarmSeverityByCore5GId(@PathVariable(name = "core_id") String core_id)
			throws ResourceNotFoundException {
		return alarmService.getPeekAlarmSeverityByCoreId(core_id);
	}

	@PostMapping("/saveAlarm")
	public ResponseEntity<String> saveSite(@RequestBody Alarm alarm) {
		alarmService.saveAlarm(alarm);
		return ResponseEntity.ok().body("Alarm Added.!");
	}

	@GetMapping("/alarm/count")
	public ResponseEntity<Long> getAlarmCount() {
		return ResponseEntity.ok().body(alarmService.getAlarmCount());
	}

}
