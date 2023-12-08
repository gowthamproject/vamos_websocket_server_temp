package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.vamos.model.Throughput;

public interface ThroughputRepository extends JpaRepository<Throughput, String> {
	List<Throughput> findAllByNodeId(String node_id);
}
