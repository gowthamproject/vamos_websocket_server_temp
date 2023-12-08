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
@Table(name = "alarmdetails_tester")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {

	@Id
	@Column(name = "alarm_id", nullable = false)
	private long alarmId;

	@Column(name = "node_type", nullable = false)
	private String nodeType;

	@Column(name = "node_name", nullable = false)
	private String nodeName;

	@Column(name = "updated_time", nullable = false)
	private String updatedTime;

	@Column(name = "severity", nullable = false)
	private String severity;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "evnet_type", nullable = true)
	private String eventType;

	@Column(name = "probable_cause", nullable = true)
	private String probableCause;

	@Column(name = "additional_message", nullable = true)
	private String additionalMessage;

	@Column(name = "acknowledged", nullable = true)
	private String acknowledged;

	@Column(name = "node_id", nullable = false)
	private String nodeId;

	public long getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(long alarmId) {
		this.alarmId = alarmId;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getProbableCause() {
		return probableCause;
	}

	public void setProbableCause(String probableCause) {
		this.probableCause = probableCause;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}

	public String getAcknowledged() {
		return acknowledged;
	}

	public void setAcknowledged(String acknowledged) {
		this.acknowledged = acknowledged;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return "Alarm [alarmId=" + alarmId + ", nodeType=" + nodeType + ", nodeName=" + nodeName + ", updatedTime="
				+ updatedTime + ", severity=" + severity + ", status=" + status + ", eventType=" + eventType
				+ ", probableCause=" + probableCause + ", additionalMessage=" + additionalMessage + ", acknowledged="
				+ acknowledged + ", nodeId=" + nodeId + "]";
	}

}
