package com.wipro.vamos.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Throughput")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThroughputEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "uplink", nullable = false)
	private long uplink;

	@Column(name = "downlink", nullable = false)
	private long downlink;

	@Column(name = "updated_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	@ManyToOne
	@JoinColumn(name="core_id", nullable = false)
	private Core5GEntity core5G;

}
