package com.wipro.vamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "throughput_tester")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Throughput {

	@Id
	@Column(name = "node_id", nullable = false)
	private String nodeId;

	@Column(name = "updated_time", nullable = false)
	private String updated_time;

	@Column(name = "uplink", nullable = false)
	private long uplink;

	@Column(name = "downlink", nullable = false)
	private long downlink;

}
