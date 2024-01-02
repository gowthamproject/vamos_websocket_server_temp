package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.ThroughputEntity;

@Repository
public interface ThroughputRepository extends JpaRepository<ThroughputEntity, String> {
	List<ThroughputEntity> findAllByCore5GId(String core_id);
}
