package com.wipro.vamos.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Core5G")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Core5GEntity {

	@Id
	@Column(name = "core_id", nullable = false)
	private String id;

	@Column(name = "core_name", nullable = false)
	private String coreName;

	@Column(name = "discovery_status", nullable = false)
	private String discoveryStatus;

	@Column(name = "endpoint")
	private String endpoint;

	@Column(name = "apikey")
	private String apikey;

	@ManyToOne
	@JoinColumn(name="site_id", nullable = false)
	private SiteEntity site;
	
	@OneToMany(mappedBy="core5G")
	private Set<GNodeBEntity> gNodeBs;
	
}
