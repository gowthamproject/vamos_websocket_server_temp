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
@Table(name = "subscriber_tester")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {

	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "imsi", nullable = true)
	private long imsi;

	@Column(name = "imei", nullable = true)
	private long imei;

	@Column(name = "ip_address", nullable = true)
	private String ipAddress;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "node_id", nullable = false)
	private String nodeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getImsi() {
		return imsi;
	}

	public void setImsi(long imsi) {
		this.imsi = imsi;
	}

	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", name=" + name + ", imsi=" + imsi + ", imei=" + imei + ", ipAddress="
				+ ipAddress + ", status=" + status + ", nodeId=" + nodeId + "]";
	}
}
