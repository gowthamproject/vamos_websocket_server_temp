package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.Core5GEntity;

@Repository
public interface Core5GRepository extends JpaRepository<Core5GEntity, String> {

	List<Core5GEntity> findBySiteId(int site_id);

}