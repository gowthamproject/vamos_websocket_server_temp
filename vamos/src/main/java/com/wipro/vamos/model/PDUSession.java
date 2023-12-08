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
@Table(name = "pdu_session_tester")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PDUSession {

	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "gnb_id", nullable = false)
	private long gnb_id;

	@Column(name = "supi", nullable = true)
	private long supi;

	@Column(name = "updated_time", nullable = false)
	private String updated_time;

	@Column(name = "node_id", nullable = false)
	private String nodeId;

}
