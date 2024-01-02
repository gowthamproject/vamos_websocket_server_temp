package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.PDUSessionEntity;

@Repository
public interface PDUSessionRepository extends JpaRepository<PDUSessionEntity, String>{
	
	List<PDUSessionEntity> findAllByCore5GId(String core_id);

}
