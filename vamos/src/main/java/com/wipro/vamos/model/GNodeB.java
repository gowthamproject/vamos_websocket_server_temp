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
@Table(name = "gnb_tester")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GNodeB {

	@Id
	@Column(name = "gnb_id", nullable = false)
	private long gnbId;

	@Column(name = "name", nullable = false)
	private String gnbName;

	@Column(name = "plmn_id", nullable = false)
	private int plmnId;

	@Column(name = "ip_address", nullable = false)
	private String ipAddress;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "node_id", nullable = false)
	private String nodeId;

	public long getGnbId() {
		return gnbId;
	}

	public void setGnbId(long gnbId) {
		this.gnbId = gnbId;
	}

	public String getGnbName() {
		return gnbName;
	}

	public void setGnbName(String gnbName) {
		this.gnbName = gnbName;
	}

	public int getPlmnId() {
		return plmnId;
	}

	public void setPlmnId(int plmnId) {
		this.plmnId = plmnId;
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
		return "GNodeB [gnbId=" + gnbId + ", gnbName=" + gnbName + ", plmnId=" + plmnId + ", ipAddress=" + ipAddress
				+ ", status=" + status + ", nodeId=" + nodeId + "]";
	}

}
