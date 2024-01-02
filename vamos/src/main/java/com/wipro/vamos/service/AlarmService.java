package com.wipro.vamos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Constant;
import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.AlarmEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Alarm;
import com.wipro.vamos.model.Core5G;
import com.wipro.vamos.repository.AlarmRepository;
import com.wipro.vamos.response.AlarmResponse;

@Service
public class AlarmService {

	@Autowired
	AlarmRepository alarmRepository;

	@Autowired
	SiteService siteService;

	@Autowired
	Core5GService core5gService;

	public List<Alarm> getAllGnodeBs() {
		return Mapper.mappingAlarmEntityToModelList(alarmRepository.findAll());
	}

	public Alarm getAlarmByID(long alarm_id) throws ResourceNotFoundException {

		Optional<AlarmEntity> alarmOptional = alarmRepository.findById(alarm_id);
		if (!alarmOptional.isPresent())
			throw new ResourceNotFoundException("Alarm not found for this id :: " + alarm_id);
		return Mapper.mappingAlarmEntityToModel(alarmOptional.get());
	}

	public List<Alarm> getAlarmByCoreID(String core_id) throws ResourceNotFoundException {

		return Mapper.mappingAlarmEntityToModelList(alarmRepository.findByCore5GId(core_id));
	}

	public AlarmResponse getAlarmCountByCoreID(String core_id) {
		List<AlarmEntity> alarms = alarmRepository.findByCore5GId(core_id);
		AlarmResponse alarmResponse = new AlarmResponse();
		Map<String, Map<String, Long>> alarmCountByStatusandSeverity = new HashMap<String, Map<String, Long>>();
		Map<String, Long> statusMap = alarms.stream()
				.collect(Collectors.groupingBy(AlarmEntity::getSeverity, Collectors.counting()));
		alarmCountByStatusandSeverity.put("Open", statusMap);
		alarmCountByStatusandSeverity.put("Closed", statusMap);
		alarmResponse.setAlarmCountByStatusandSeverity(alarmCountByStatusandSeverity);
		alarmResponse.setCore_id(core_id);
		return alarmResponse;
	}

	public void saveAlarm(Alarm alarm) {
		alarmRepository.save(Mapper.mappingAlarmModelToEntity(alarm));
	}

	public long getAlarmCountBySeverityAndState(List<String> coreIdList, String status, String severity) {

		return alarmRepository.countByCore5GIdInAndStatusAndSeverity(coreIdList, status, severity);
	}

	public String getPeekAlarmSeverityBySiteId(int site_id) throws ResourceNotFoundException {
		List<Core5G> core5gList = core5gService.getCore5GBySiteId(site_id);

		List<String> coreIdList = core5gList.stream().map(core -> core.getCoreId()).collect(Collectors.toList());
		long severityCount = 0;

		severityCount = getAlarmCountBySeverityAndState(coreIdList, Constant.ALARM_STATUS_OPEN, Constant.CRITICAL);
		if (severityCount > 0)
			return Constant.CRITICAL;
		severityCount = getAlarmCountBySeverityAndState(coreIdList, Constant.ALARM_STATUS_OPEN, Constant.MAJOR);
		if (severityCount > 0)
			return Constant.MAJOR;
		severityCount = getAlarmCountBySeverityAndState(coreIdList, Constant.ALARM_STATUS_OPEN, Constant.WARNING);
		if (severityCount > 0)
			return Constant.WARNING;

		return Constant.NORMAL;	
	}

	public String getPeekAlarmSeverityByCoreId(String core_id) throws ResourceNotFoundException {
		Map<String, Long> openAlarmSeverityCountMap = getAlarmCountByCoreID(core_id).getAlarmCountByStatusandSeverity()
				.get(Constant.ALARM_STATUS_OPEN);
		if (openAlarmSeverityCountMap.get(Constant.CRITICAL) > 0)
			return Constant.CRITICAL;
		if (openAlarmSeverityCountMap.get(Constant.MAJOR) > 0)
			return Constant.MAJOR;
		if (openAlarmSeverityCountMap.get(Constant.WARNING) > 0)
			return Constant.WARNING;

		return Constant.NORMAL;
	}

}
