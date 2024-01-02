package com.wipro.vamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.LocationEntity;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

	LocationEntity findByGNodeBId(long gnb_id);

	LocationEntity findBySiteId(long site_id);
}