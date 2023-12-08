package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.vamos.model.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>{

	List<Alarm> findByStatus(String status);

	List<Alarm> findAllByNodeId(String node_id);
}
