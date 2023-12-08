package com.wipro.vamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.vamos.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	List<Subscriber> findByStatus(String status);

	List<Subscriber> findAllByNodeId(String node_id);
}