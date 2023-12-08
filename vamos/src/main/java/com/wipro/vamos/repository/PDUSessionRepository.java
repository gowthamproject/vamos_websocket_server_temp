package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.vamos.model.PDUSession;

public interface PDUSessionRepository extends JpaRepository<PDUSession, String>{
	
	List<PDUSession> findAllByNodeId(String node_id);

}
