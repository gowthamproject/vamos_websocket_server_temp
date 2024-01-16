package com.wipro.vamos.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Enterprise")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "enterprise_id")
	private int id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@OneToMany(mappedBy="enterprise")
	private Set<SiteEntity> sites;

}
