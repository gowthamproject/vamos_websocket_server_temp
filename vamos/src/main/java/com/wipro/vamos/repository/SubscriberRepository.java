package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.vamos.entity.SubscriberEntity;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {

	List<SubscriberEntity> findByStatus(String status);

	List<SubscriberEntity> findByCore5GId(String node_id);
}