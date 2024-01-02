package com.wipro.vamos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Site {

	private String id;

	private String name;

	private String address;

	private String latitude;

	private String longitude;

	private List<Core5G> core5gs;

}
