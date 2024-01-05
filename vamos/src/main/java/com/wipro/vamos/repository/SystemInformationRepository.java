package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.SystemInformationEntity;

@Repository
public interface SystemInformationRepository extends JpaRepository<SystemInformationEntity, Long> {


	List<SystemInformationEntity> findByCore5GId(String core_id);

}