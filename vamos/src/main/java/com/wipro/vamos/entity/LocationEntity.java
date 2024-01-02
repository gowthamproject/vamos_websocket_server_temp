package com.wipro.vamos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Location")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "street")
	private String street;

	@Column(name = "region_state")
	private String regionState;

	@Column(name = "country")
	private String country;

	@Column(name = "zipcode")
	private long zipcode;

	@Column(name = "latitude", nullable = false)
	private long latitude;

	@Column(name = "longitude", nullable = false)
	private long longitude;

	@ManyToOne
	@JoinColumn(name = "site_id", nullable = false)
	private SiteEntity site;

	@ManyToOne
	@JoinColumn(name = "gnb_id", nullable = false)
	private GNodeBEntity GNodeB;

	@ManyToOne
	@JoinColumn(name = "core_id", nullable = false)
	private Core5GEntity core5G;

}
