package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.AlarmEntity;

@Repository
public interface AlarmRepository extends JpaRepository<AlarmEntity, Long>{

	List<AlarmEntity> findByStatus(String status);

	List<AlarmEntity> findByCore5GId(String core_id);

	long countByCore5GIdInAndStatusAndSeverity(List<String> core_id, String status, String severity);

	Page<AlarmEntity> findAll(Specification<AlarmEntity> spec, Pageable pageable);

}
