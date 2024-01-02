package com.wipro.vamos.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PDUSession {

	private long id;

	@Builder.Default
	private int sessionCount = 0;

	private int msisdn;

	private Date updatedTime;

}
