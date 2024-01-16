package com.wipro.vamos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.CPE;
import com.wipro.vamos.model.Core5G;
import com.wipro.vamos.model.Enterprise;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.model.Location;
import com.wipro.vamos.model.Site;
import com.wipro.vamos.response.HierarchyResponse;
import com.wipro.vamos.response.P5gNetworkEnterprise;

@Service
public class HierarchyService {

	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	SiteService siteService;
	@Autowired
	Core5GService core5gService;
	@Autowired
	GNodeBService gNodeBService;
	@Autowired
	CPEService cpeService;
	@Autowired
	LocationService locationService;
	@Autowired
	AlarmService alarmService;

	public HierarchyResponse getAllHierarchy() {

		HierarchyResponse hierarchyResponse = new HierarchyResponse();
		P5gNetworkEnterprise enterpriseResponse;
		com.wipro.vamos.response.Site siteResponse;
		com.wipro.vamos.response.Core5G core5gResponse;
		com.wipro.vamos.response.GNodeB gNodeBResponse;
		com.wipro.vamos.response.CPE cpeResponse;

		List<Enterprise> enterprisesList = enterpriseService.getAllEnterprise();
		List<P5gNetworkEnterprise> enterprisesResponseList = new ArrayList<>();
		for (Enterprise enterprise : enterprisesList) {
			enterpriseResponse = new P5gNetworkEnterprise();
			enterpriseResponse.setEnterpriseId(enterprise.getEnterpriseId());
			enterpriseResponse.setEnterpriseName(enterprise.getEnterpriseName());

			List<Site> siteList = siteService.getSiteByEnterpriseId(enterprise.getEnterpriseId());
			List<com.wipro.vamos.response.Site> siteResponseList = new ArrayList<>();
			for (Site site : siteList) {
				siteResponse = new com.wipro.vamos.response.Site();
				siteResponse.setSiteName(site.getSiteName());
				siteResponse.setSiteId(site.getSiteId());
				try {
					siteResponse.setPeekAlarmSeverity(alarmService.getPeekAlarmSeverityBySiteId(site.getSiteId()));
				} catch (ResourceNotFoundException e) {
				}
				Location siteLocation;
				try {
					siteLocation = locationService.getLocationBySiteID(site.getSiteId());
					siteResponse.setLocation(castLoation(siteLocation));
				} catch (ResourceNotFoundException e) {
				}
				List<Core5G> core5gList;
				List<com.wipro.vamos.response.Core5G> core5gResponseList = new ArrayList<>();
				try {
					core5gList = core5gService.getCore5GBySiteId(site.getSiteId());
					for (Core5G core5g : core5gList) {
						core5gResponse = new com.wipro.vamos.response.Core5G();
						core5gResponse.setApikey(core5g.getApikey());
						core5gResponse.setCoreId(core5g.getCoreId());
						core5gResponse.setDiscoveryStatus(core5g.getDiscoveryStatus());
						core5gResponse.setEndpoint(core5g.getEndpoint());
						core5gResponse.setName(core5g.getName());

						List<GNodeB> gNodeBList = null;
						List<com.wipro.vamos.response.GNodeB> gNodeBResponseList = new ArrayList<>();
						try {
							gNodeBList = gNodeBService.getGNodeBByCoreID(core5g.getCoreId());
							for (GNodeB gNodeB : gNodeBList) {
								gNodeBResponse = new com.wipro.vamos.response.GNodeB();
								gNodeBResponse.setGnbId(gNodeB.getGnbId());
								gNodeBResponse.setGnbName(gNodeB.getGnbName());
								gNodeBResponse.setIpAddress(gNodeB.getIpAddress());
								gNodeBResponse.setPlmnId(gNodeB.getPlmnId());
								gNodeBResponse.setStatus(gNodeB.getStatus());
								Location gNodeBlocation = null;
								try {
									gNodeBlocation = locationService.getLocationByGNodeBID(gNodeB.getGnbId());
								} catch (ResourceNotFoundException e) {
								}
								gNodeBResponse.setLocation(castLoation(gNodeBlocation));

								List<CPE> cpeList = null;
								List<com.wipro.vamos.response.CPE> cpeResponseList = new ArrayList<>();
								try {
									cpeList = cpeService.getCPEByGNodeBID(gNodeB.getGnbId());
									for (CPE cpe : cpeList) {
										cpeResponse = new com.wipro.vamos.response.CPE();
										cpeResponse.setCpeId(cpe.getCpeId());
										cpeResponse.setCpeName(cpe.getCpeName());
										cpeResponse.setIpAddress(cpe.getIpAddress());
										cpeResponse.setStatus(cpe.getStatus());
										cpeResponseList.add(cpeResponse);
									}
								} catch (ResourceNotFoundException e) {
								}
								gNodeBResponse.setCpes(cpeResponseList);
								gNodeBResponseList.add(gNodeBResponse);
							}
						} catch (ResourceNotFoundException e) {
							e.printStackTrace();
						}
						core5gResponse.setGNodeBs(gNodeBResponseList);
						try {
							core5gResponse.setPeekAlarmSeverity(
									alarmService.getPeekAlarmSeverityByCoreId(core5g.getCoreId()));
						} catch (ResourceNotFoundException e) {
						}
						core5gResponse.setAlarmCount(alarmService.getAlarmCountByCoreID(core5g.getCoreId()));
						core5gResponseList.add(core5gResponse);
					}
				} catch (ResourceNotFoundException e) {
				}
				siteResponse.setCore5gs(core5gResponseList);
				siteResponseList.add(siteResponse);
			}
			enterpriseResponse.setSites(siteResponseList);
			enterprisesResponseList.add(enterpriseResponse);
		}
		hierarchyResponse.setP5gNetworkEnterprises(enterprisesResponseList);
		return hierarchyResponse;
	}

	private com.wipro.vamos.response.Location castLoation(Location siteLocation) {
		if (siteLocation == null)
			return null;
		com.wipro.vamos.response.Location locationResponse = new com.wipro.vamos.response.Location();
		locationResponse = new com.wipro.vamos.response.Location();
		locationResponse.setCountry(siteLocation.getCountry());
		locationResponse.setLatitude(siteLocation.getLatitude());
		locationResponse.setLongitude(siteLocation.getLongitude());
		locationResponse.setId(siteLocation.getId());
		locationResponse.setRegionState(siteLocation.getRegionState());
		locationResponse.setStreet(siteLocation.getStreet());
		locationResponse.setType(siteLocation.getType());
		locationResponse.setZipcode(siteLocation.getZipcode());
		return locationResponse;
	}

}
