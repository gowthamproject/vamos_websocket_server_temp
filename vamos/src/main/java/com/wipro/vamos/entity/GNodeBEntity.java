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
@Table(name = "GNodeB")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GNodeBEntity {

	@Id
	@Column(name = "gnb_id", nullable = false)
	private long id;

	@Column(name = "gnb_name", nullable = false)
	private String gnbName;

	@Column(name = "ip_address", nullable = false)
	private String ipAddress;

	@Column(name = "plmn_id", nullable = false)
	private long plmnId;

	@Column(name = "status", nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "core_id", nullable = false)
	private Core5GEntity core5G;
	
	@OneToMany(mappedBy="GNodeB")
	private Set<CPEEntity> cpes;

}
