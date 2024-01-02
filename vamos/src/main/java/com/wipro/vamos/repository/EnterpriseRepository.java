package com.wipro.vamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.EnterpriseEntity;

@Repository
public interface EnterpriseRepository extends JpaRepository<EnterpriseEntity, Integer> {

}