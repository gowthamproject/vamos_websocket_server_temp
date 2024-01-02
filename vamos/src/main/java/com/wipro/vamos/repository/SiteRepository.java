package com.wipro.vamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.SiteEntity;

@Repository
public interface SiteRepository extends JpaRepository<SiteEntity, Integer> {

}