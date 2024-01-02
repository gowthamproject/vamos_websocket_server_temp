package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.GNodeBEntity;

@Repository
public interface GNodeBRepository extends JpaRepository<GNodeBEntity, Long> {

	List<GNodeBEntity> findByStatus(String status);

	List<GNodeBEntity> findByCore5GId(String core_id);

	void deleteByIdIn(List<Long> gNodeBIds);
}