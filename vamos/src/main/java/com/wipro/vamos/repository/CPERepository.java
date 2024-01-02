package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.CPEEntity;

@Repository
public interface CPERepository extends JpaRepository<CPEEntity, Long> {

	List<CPEEntity> findByStatus(String status);

	List<CPEEntity> findByGNodeBId(long gnb_id);
}