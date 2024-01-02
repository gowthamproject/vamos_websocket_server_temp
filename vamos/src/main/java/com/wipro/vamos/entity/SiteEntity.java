package com.wipro.vamos.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Site")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "site_id")
	private int id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="enterprise_id",  nullable = false)
	private EnterpriseEntity enterprise;

	@OneToMany(mappedBy="site")
	private Set<Core5GEntity> core5gs;
}
