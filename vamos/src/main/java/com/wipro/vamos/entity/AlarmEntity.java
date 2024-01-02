package com.wipro.vamos.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Alarm")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmEntity {

	@Id
	@Column(name = "alarm_id", nullable = false)
	private long id;

	@Column(name = "node_type", nullable = false)
	private String nodeType;

	@Column(name = "node_name", nullable = false)
	private String nodeName;

	@Column(name = "updated_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	@Column(name = "severity", nullable = false)
	private String severity;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "evnet_type")
	private String evnetType;

	@Column(name = "specific_problem")
	private String specificProblem;

	@Column(name = "additional_message")
	private String additionalMessage;

	@Column(name = "acknowledged", nullable = false)
	private String acknowledged;

	@ManyToOne
	@JoinColumn(name = "gnb_id")
	private GNodeBEntity gNodeB;

	@ManyToOne
	@JoinColumn(name = "core_id", nullable = false)
	private Core5GEntity core5G;

}
