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
@Table(name = "PDUSession")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PDUSessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "session_count", nullable = false)
	private int sessionCount;
	
	@Column(name = "msisdn", nullable = false)
	private int msisdn;

	@Column(name = "updated_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	@ManyToOne
	@JoinColumn(name = "serving_gnb_id", nullable = false)
	private GNodeBEntity servingGnbId;

	@ManyToOne
	@JoinColumn(name = "core_id", nullable = false)
	private Core5GEntity core5G;

}
