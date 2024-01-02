package com.wipro.vamos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Subscriber")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberEntity {

	@Id
	@Column(name = "subscriber_id", nullable = false)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "imsi")
	private long imsi;

	@Column(name = "imei")
	private long imei;

	@Column(name = "msisdn")
	private long msisdn;

	@Column(name = "ip_address", nullable = false)
	private String ipAddress;

	@Column(name = "status", nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "core_id", nullable = false)
	private Core5GEntity core5G;

}
