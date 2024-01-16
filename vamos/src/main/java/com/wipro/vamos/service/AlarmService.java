package com.wipro.vamos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Constant;
import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.AlarmEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Alarm;
import com.wipro.vamos.model.Core5G;
import com.wipro.vamos.repository.AlarmRepository;
import com.wipro.vamos.response.AlarmCount;
import com.wipro.vamos.specification.AlarmSpecification;

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

	public AlarmCount getAlarmCountByCoreID(String core_id) {
		List<AlarmEntity> alarms = alarmRepository.findByCore5GId(core_id);
		if (alarms == null || alarms.isEmpty())
			return new AlarmCount();

		AlarmCount alarmCount = new AlarmCount();
		alarmCount.setTotalAlarmCount(alarms.size());
		Map<String, List<AlarmEntity>> openClosedAlarmMap = alarms.stream()
				.collect(Collectors.groupingBy(AlarmEntity::getStatus));
		alarmCount.setOpenedAlarmCount(openClosedAlarmMap.get(Constant.ALARM_STATUS_OPEN).size());
		alarmCount.setClosedAlarmCount(openClosedAlarmMap.get(Constant.ALARM_STATUS_CLOSED).size());

		Map<String, Map<String, Long>> alarmCountByStatusandSeverity = new HashMap<String, Map<String, Long>>();

		for (String alarmStatus : openClosedAlarmMap.keySet()) {
			Map<String, Long> statusMap = openClosedAlarmMap.get(alarmStatus).stream()
					.collect(Collectors.groupingBy(AlarmEntity::getSeverity, Collectors.counting()));
			alarmCountByStatusandSeverity.put(alarmStatus, statusMap);
		}
		alarmCount.setAlarmCountByStatusandSeverity(alarmCountByStatusandSeverity);
		alarmCount.setCore_id(core_id);
		return alarmCount;
	}

	public void saveAlarm(Alarm alarm) {
		alarmRepository.save(Mapper.mappingAlarmModelToEntity(alarm));
	}

	public long getAlarmCountBySeverityAndState(List<String> coreIdList, String status, String severity) {

		return alarmRepository.countByCore5GIdInAndStatusAndSeverity(coreIdList, status, severity);
	}

	public String getPeekAlarmSeverityBySiteId(int site_id) throws ResourceNotFoundException {
		List<Core5G> core5gList = core5gService.getCore5GBySiteId(site_id);
		if (core5gList == null || core5gList.isEmpty())
			throw new ResourceNotFoundException("Alarm severity not found for this site id " + site_id);

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

		AlarmCount alarmCount = getAlarmCountByCoreID(core_id);
		if (alarmCount == null)
			throw new ResourceNotFoundException("Alarm severity not found for this core_id " + core_id);

		Map<String, Map<String, Long>> alarmCountMap = alarmCount.getAlarmCountByStatusandSeverity();
		if (alarmCountMap == null)
			throw new ResourceNotFoundException("Alarm severity not found for this core_id " + core_id);

		Map<String, Long> openAlarmSeverityCountMap = alarmCount.getAlarmCountByStatusandSeverity()
				.get(Constant.ALARM_STATUS_OPEN);
		if (openAlarmSeverityCountMap.get(Constant.CRITICAL) > 0)
			return Constant.CRITICAL;
		if (openAlarmSeverityCountMap.get(Constant.MAJOR) > 0)
			return Constant.MAJOR;
		if (openAlarmSeverityCountMap.get(Constant.WARNING) > 0)
			return Constant.WARNING;

		return Constant.NORMAL;
	}

	public long getAlarmCount() {
		return alarmRepository.count();
	}

	public void updateAlarm(Alarm alarm) {
		alarmRepository.save(Mapper.mappingAlarmModelToEntity(alarm));
	}

	public Page<AlarmEntity> filterAlarms(String severity, Double fromDate, Double toDate, Integer page, Integer size,
			String sortBy) {
		Specification<AlarmEntity> spec = Specification.where(null);

		if (severity != null) {
			spec = spec.and(AlarmSpecification.filterBySeverity(severity));
		}
		// Sorting
		Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
		// Paging
		Pageable pageable = PageRequest.of(page, size);

		return alarmRepository.findAll(spec, pageable);
	}
	
}
