package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.vamos.model.GNodeB;

public interface GNodeBRepository extends JpaRepository<GNodeB, Long> {

	List<GNodeB> findByStatus(String status);

	List<GNodeB> findAllByNodeId(String node_id);
}