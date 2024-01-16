package com.wipro.vamos.specification;

import org.springframework.data.jpa.domain.Specification;

import com.wipro.vamos.entity.AlarmEntity;

// ProductSpecification.java
public class AlarmSpecification {

	public static Specification<AlarmEntity> filterBySeverity(String severity) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("severity"),
				"%" + severity + "%");
	}
}