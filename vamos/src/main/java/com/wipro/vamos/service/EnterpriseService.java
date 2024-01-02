package com.wipro.vamos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.EnterpriseEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.repository.EnterpriseRepository;
import com.wipro.vamos.response.Core5G;
import com.wipro.vamos.response.Enterprise;
import com.wipro.vamos.response.GNodeB;
import com.wipro.vamos.response.Location;
import com.wipro.vamos.response.P5gNetworkEnterprise;
import com.wipro.vamos.response.Site;

@Service
public class EnterpriseService {

	@Autowired
	EnterpriseRepository enterpriseRepository;

	public Enterprise getAllHierarchy() {
		Enterprise enterprise = new Enterprise();
		List<P5gNetworkEnterprise> p5gNetworkEnterprises = new ArrayList<P5gNetworkEnterprise>();
		for (int ent_index = 1; ent_index <= 2; ent_index++) {

			P5gNetworkEnterprise enterpriseP5gNetworkEnterprise = new P5gNetworkEnterprise();
			enterpriseP5gNetworkEnterprise.setName("Enterprise-" + ent_index);
			enterpriseP5gNetworkEnterprise.setId("Ent-00" + ent_index);

			List<Site> sites = new ArrayList<Site>();

			for (int site_index = 1; site_index <= 2; site_index++) {
				Site site = new Site();
				if (site_index == 1) {
					site.setAddress("Wiro Gate-1" + site_index + ", Electronic City, California");
				} else {
					site.setAddress("Wiro Gate-1" + site_index + ", Electronic City, Texas");
				}

				site.setId("3200" + site_index);

				if (site_index == 1) {
					site.setLatitude("-121.934463501");
					site.setLongitude("-121.934463501");
				} else {
					site.setLatitude("30.267200");
					site.setLongitude("-97.743100000");
				}

				site.setName("Test Site - " + site_index);

				List<Core5G> core5gs = new ArrayList<Core5G>();

				for (int core_index = 1; core_index <= 2; core_index++) {
					Core5G core5g = new Core5G();
					core5g.setApiKey("APIKEY-890978979-" + core_index);
					core5g.setEndpoint("coreurl_" + core_index + ".co.in");

					if (ent_index == 1) {
						if (site_index == 1) {
							if (core_index == 1)
								core5g.setId("1045173342497102197");
							else
								core5g.setId("230451733424971555");
						} else {
							if (core_index == 1)
								core5g.setId("78686868684343");
							else
								core5g.setId("5434534543535435");
						}
					} else {
						if (site_index == 1) {
							if (core_index == 1)
								core5g.setId("686868683434");
							else
								core5g.setId("5435433246565");
						} else {
							if (core_index == 1)
								core5g.setId("56565632455454");
							else
								core5g.setId("8656456465464654");
						}
					}

					core5g.setName("Core 5G -" + core_index);
					core5g.setStatus("Active");

					/*
					 * if (core_index == 1) { core5g.setRegion_state("California");
					 * 
					 * } else { core5g.setRegion_state("Texas");
					 * 
					 * }
					 */

					/*
					 * if (core_index == 1) { core5g.setLatitude("-121.934463501");
					 * core5g.setLongitude("-121.934463501"); } else {
					 * core5g.setLatitude("30.267200"); core5g.setLongitude("-97.743100000"); }
					 */

					List<GNodeB> gNodeBs = new ArrayList<GNodeB>();
					for (int gnb_index = 1; gnb_index <= 4; gnb_index++) {
						GNodeB gNodeB = new GNodeB();
						gNodeB.setGnbId(gnb_index);
						gNodeB.setName("GNB-1-0-0-" + gnb_index);
						gNodeB.setIpAddress("192.168.26." + gnb_index);
						gNodeB.setPlmnId(1010);
						if (gnb_index % 2 == 0)
							gNodeB.setStatus("Connected");
						else
							gNodeB.setStatus("Disconnected");

						Location location = new Location();
						if (gnb_index == 1) {
							location.setLatitude("30.267200");
							location.setLongitude("-97.743100000");
							location.setRegion_state("Texas");
						} else if (gnb_index == 2) {
							location.setLatitude("-121.934463501");
							location.setLongitude("-121.934463501");
							location.setRegion_state("California");
						} else if (gnb_index == 3) {
							location.setLatitude("12.972442");
							location.setLongitude("77.580643000");
							location.setRegion_state("Karnataka");
						} else if (gnb_index == 4) {
							location.setLatitude("41.081445");
							location.setLongitude("12.972442");
							location.setRegion_state("Ohio");
						}
						gNodeB.setLocation(location);

						gNodeBs.add(gNodeB);
					}

					core5g.setGNodeBs(gNodeBs);
					core5gs.add(core5g);
				}
				site.setCore5gs(core5gs);
				sites.add(site);
			}
			enterpriseP5gNetworkEnterprise.setSites(sites);
			p5gNetworkEnterprises.add(enterpriseP5gNetworkEnterprise);
			enterprise.setP5gNetworkEnterprises(p5gNetworkEnterprises);
		}
		return enterprise;
	}

	public com.wipro.vamos.model.Enterprise getEnterpriseById(int ent_id) throws ResourceNotFoundException {
		Optional<EnterpriseEntity> enterpriseOptional = enterpriseRepository.findById(ent_id);
		if (!enterpriseOptional.isPresent())
			throw new ResourceNotFoundException("Enterprise not found for this id :: " + ent_id);
		return Mapper.enterpriseEntityToModel(enterpriseOptional.get());
	}

	public void saveEnterprise(com.wipro.vamos.model.Enterprise enterprise) {
		enterpriseRepository.save(Mapper.enterpriseModelToEntity(enterprise));
	}

	public List<com.wipro.vamos.model.Enterprise> getAllEnterprise() {
		return Mapper.enterpriseEntityToModelList(enterpriseRepository.findAll());
	}

}
