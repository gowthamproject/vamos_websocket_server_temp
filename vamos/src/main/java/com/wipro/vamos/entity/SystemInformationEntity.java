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
@Table(name = "SystemInformation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemInformationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "host_name", nullable = false)
	private String hostName;

	@Column(name = "ip_address", nullable = false)
	private String ipAddress;

	@Column(name = "node_type", nullable = false)
	private String nodeType;

	@Column(name = "updated_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	@Column(name = "memory_usage_percentage", nullable = false)
	private String memoryUsagePercentage;

	@Column(name = "cpu_utilization_percentage", nullable = false)
	private String cpuUtilizationPercentage;

	@Column(name = "disk_usage_percentage", nullable = false)
	private String diskUsagePercentage;

	@Column(name = "available_memory_gb", nullable = false)
	private String availableMemoryGB;

	@Column(name = "total_memory_gb", nullable = false)
	private String totalMemoryGB;

	@Column(name = "available_disk_gb", nullable = false)
	private String availableDiskGB;

	@Column(name = "total_disk_gb", nullable = false)
	private String totalDiskGB;

	@ManyToOne
	@JoinColumn(name = "core_id", nullable = false)
	private Core5GEntity core5G;

}
