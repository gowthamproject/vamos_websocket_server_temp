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
@Table(name = "CPE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CPEEntity {

	@Id
	@Column(name = "cpe_id", nullable = false)
	private long id;

	@Column(name = "cpe_name", nullable = false)
	private String cpeName;

	@Column(name = "ip_address", nullable = false)
	private String ipAddress;

	@Column(name = "status", nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "gnb_id", nullable = false)
	private GNodeBEntity GNodeB;

}
