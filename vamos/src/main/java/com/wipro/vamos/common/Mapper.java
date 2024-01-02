package com.wipro.vamos.common;

import java.util.ArrayList;
import java.util.List;

import com.wipro.vamos.entity.AlarmEntity;
import com.wipro.vamos.entity.CPEEntity;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.EnterpriseEntity;
import com.wipro.vamos.entity.GNodeBEntity;
import com.wipro.vamos.entity.LocationEntity;
import com.wipro.vamos.entity.PDUSessionEntity;
import com.wipro.vamos.entity.SiteEntity;
import com.wipro.vamos.entity.SubscriberEntity;
import com.wipro.vamos.entity.ThroughputEntity;
import com.wipro.vamos.model.Alarm;
import com.wipro.vamos.model.CPE;
import com.wipro.vamos.model.Core5G;
import com.wipro.vamos.model.Enterprise;
import com.wipro.vamos.model.GNodeB;
import com.wipro.vamos.model.Location;
import com.wipro.vamos.model.PDUSession;
import com.wipro.vamos.model.Site;
import com.wipro.vamos.model.Subscriber;
import com.wipro.vamos.model.Throughput;

public class Mapper {

	public static AlarmEntity mappingAlarmModelToEntity(Alarm alarm) {
		AlarmEntity alarmEntity = new AlarmEntity();
		alarmEntity.setId(alarm.getAlarmId());
		alarmEntity.setAcknowledged(alarm.getAcknowledged());
		alarmEntity.setAdditionalMessage(alarm.getAdditionalMessage());
		alarmEntity.setEvnetType(alarm.getEvnetType());
		alarmEntity.setNodeName(alarm.getNodeName());
		alarmEntity.setNodeType(alarm.getNodeType());
		alarmEntity.setSeverity(alarm.getSeverity());
		alarmEntity.setSpecificProblem(alarm.getSpecificProblem());
		alarmEntity.setStatus(alarm.getStatus());
		alarmEntity.setUpdatedTime(alarm.getUpdatedTime());

		Core5GEntity core5gEntity = new Core5GEntity();
		core5gEntity.setId(alarm.getCoreId());
		alarmEntity.setCore5G(core5gEntity);
		return alarmEntity;
	}

	public static Alarm mappingAlarmEntityToModel(AlarmEntity alarmEntity) {
		Alarm alarm = new Alarm();
		alarm.setAlarmId(alarmEntity.getId());
		alarm.setAcknowledged(alarmEntity.getAcknowledged());
		alarm.setAdditionalMessage(alarmEntity.getAdditionalMessage());
		alarm.setEvnetType(alarmEntity.getEvnetType());
		alarm.setNodeName(alarmEntity.getNodeName());
		alarm.setNodeType(alarmEntity.getNodeType());
		alarm.setSeverity(alarmEntity.getSeverity());
		alarm.setSpecificProblem(alarmEntity.getSpecificProblem());
		alarm.setStatus(alarmEntity.getStatus());
		alarm.setUpdatedTime(alarm.getUpdatedTime());
		alarm.setCoreId(alarmEntity.getCore5G().getId());
		return alarm;
	}

	public static List<Alarm> mappingAlarmEntityToModelList(List<AlarmEntity> alarmEntityList) {
		List<Alarm> alarmList = new ArrayList<Alarm>();
		for (AlarmEntity alarmEntity : alarmEntityList) {
			alarmList.add(mappingAlarmEntityToModel(alarmEntity));
		}
		return alarmList;
	}

	public static Core5G core5gEntityToModel(Core5GEntity core5gEntity) {
		Core5G core5g = new Core5G();
		core5g.setApikey(core5gEntity.getApikey());
		core5g.setCoreId(core5gEntity.getId());
		core5g.setDiscoveryStatus(core5gEntity.getDiscoveryStatus());
		core5g.setName(core5gEntity.getCoreName());
		core5g.setEndpoint(core5gEntity.getEndpoint());
		return core5g;
	}

	public static List<Core5G> core5gEntityToModelList(List<Core5GEntity> core5gEntityList) {
		List<Core5G> core5gList = new ArrayList<Core5G>();
		for (Core5GEntity core5gEntity : core5gEntityList) {
			core5gList.add(core5gEntityToModel(core5gEntity));
		}
		return core5gList;
	}

	public static Core5GEntity core5gModelToEntity(Core5G core5g) {
		Core5GEntity core5gEntity = new Core5GEntity();
		core5gEntity.setApikey(core5g.getApikey());
		core5gEntity.setId(core5g.getCoreId());
		core5gEntity.setDiscoveryStatus(core5g.getDiscoveryStatus());
		core5gEntity.setCoreName(core5g.getName());
		core5gEntity.setEndpoint(core5g.getEndpoint());
		return core5gEntity;
	}

	public static CPE cpeEntityToModel(CPEEntity cpeEntity) {
		CPE cpe = new CPE();
		cpe.setCpeId(cpeEntity.getId());
		cpe.setCpeName(cpeEntity.getCpeName());
		cpe.setStatus(cpeEntity.getStatus());
		cpe.setIpAddress(cpeEntity.getIpAddress());
		return cpe;
	}

	public static List<CPE> cpeEntityToModelList(List<CPEEntity> cpeEntityList) {
		List<CPE> cpeList = new ArrayList<CPE>();
		for (CPEEntity cpeEntity : cpeEntityList) {
			cpeList.add(cpeEntityToModel(cpeEntity));
		}
		return cpeList;
	}

	public static CPEEntity cpeModelToEntity(CPE cpe) {
		CPEEntity cpeEntity = new CPEEntity();
		cpe.setCpeId(cpeEntity.getId());
		cpe.setCpeName(cpeEntity.getCpeName());
		cpe.setStatus(cpeEntity.getStatus());
		cpe.setIpAddress(cpeEntity.getIpAddress());
		return cpeEntity;
	}

	public static Enterprise enterpriseEntityToModel(EnterpriseEntity enterpriseEntity) {
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterpriseId(enterpriseEntity.getId());
		enterprise.setEnterpriseName(enterpriseEntity.getName());
		return enterprise;
	}

	public static List<Enterprise> enterpriseEntityToModelList(List<EnterpriseEntity> enterpriseEntityList) {
		List<Enterprise> enterpriseList = new ArrayList<>();
		for (EnterpriseEntity enterprise : enterpriseEntityList) {
			enterpriseList.add(enterpriseEntityToModel(enterprise));
		}
		return enterpriseList;
	}

	public static EnterpriseEntity enterpriseModelToEntity(Enterprise enterprise) {
		EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
		enterpriseEntity.setId(enterprise.getEnterpriseId());
		enterpriseEntity.setName(enterprise.getEnterpriseName());
		return enterpriseEntity;
	}

	public static GNodeB gNodeBEntityToModel(GNodeBEntity gNodeBEntity) {
		GNodeB gNodeB = new GNodeB();
		gNodeB.setGnbId(gNodeBEntity.getId());
		gNodeB.setGnbName(gNodeBEntity.getGnbName());
		gNodeB.setIpAddress(gNodeBEntity.getIpAddress());
		gNodeB.setPlmnId(gNodeBEntity.getPlmnId());
		gNodeB.setStatus(gNodeBEntity.getStatus());
		return gNodeB;
	}

	public static List<GNodeB> gNodeBEntityToModelList(List<GNodeBEntity> gNodeBEntityList) {
		List<GNodeB> gNodeBList = new ArrayList<GNodeB>();
		for (GNodeBEntity gNodeBEntity : gNodeBEntityList) {
			gNodeBList.add(gNodeBEntityToModel(gNodeBEntity));
		}
		return gNodeBList;
	}

	public static GNodeBEntity gNodeBModelToEntity(GNodeB gNodeB) {
		GNodeBEntity gNodeBEntity = new GNodeBEntity();
		gNodeBEntity.setId(gNodeB.getGnbId());
		gNodeBEntity.setGnbName(gNodeB.getGnbName());
		gNodeBEntity.setIpAddress(gNodeB.getIpAddress());
		gNodeBEntity.setPlmnId(gNodeB.getPlmnId());
		gNodeBEntity.setStatus(gNodeB.getStatus());
		return gNodeBEntity;
	}

	public static Location locationEntityToModel(LocationEntity locationEntity) {
		Location location = new Location();
		location.setCountry(locationEntity.getCountry());
		location.setId(locationEntity.getId());
		location.setLatitude(locationEntity.getLatitude());
		location.setLongitude(locationEntity.getLongitude());
		location.setRegionState(locationEntity.getRegionState());
		location.setStreet(locationEntity.getStreet());
		location.setType(locationEntity.getType());
		location.setZipcode(locationEntity.getZipcode());
		return location;
	}

	public static List<Location> locationEntityToModelList(List<LocationEntity> locationEntityList) {
		List<Location> locationList = new ArrayList<Location>();
		for (LocationEntity locationEntity : locationEntityList) {
			locationList.add(locationEntityToModel(locationEntity));
		}
		return locationList;
	}

	public static LocationEntity locationModelToEntity(Location location) {
		LocationEntity locationEntity = new LocationEntity();
		locationEntity.setCountry(location.getCountry());
		locationEntity.setId(location.getId());
		locationEntity.setLatitude(location.getLatitude());
		locationEntity.setLongitude(location.getLongitude());
		locationEntity.setRegionState(location.getRegionState());
		locationEntity.setStreet(location.getStreet());
		locationEntity.setType(location.getType());
		locationEntity.setZipcode(location.getZipcode());
		return locationEntity;
	}

	public static PDUSession pduSessionEntityToModel(PDUSessionEntity pduSessionEntity) {
		PDUSession pduSession = new PDUSession();
		pduSession.setId(pduSessionEntity.getId());
		pduSession.setMsisdn(pduSessionEntity.getMsisdn());
		pduSession.setSessionCount(pduSessionEntity.getSessionCount());
		pduSession.setUpdatedTime(pduSessionEntity.getUpdatedTime());
		return pduSession;
	}

	public static List<PDUSession> pduSessionEntityToModelList(List<PDUSessionEntity> pduSessionEntityList) {
		List<PDUSession> pduSessionList = new ArrayList<PDUSession>();
		for (PDUSessionEntity pduSessionEntity : pduSessionEntityList) {
			pduSessionList.add(pduSessionEntityToModel(pduSessionEntity));
		}
		return pduSessionList;
	}

	public static PDUSessionEntity pduSessionModelToEntity(PDUSession pduSession) {
		PDUSessionEntity pduSessionEntity = new PDUSessionEntity();
		pduSessionEntity.setId(pduSession.getId());
		pduSessionEntity.setMsisdn(pduSession.getMsisdn());
		pduSessionEntity.setSessionCount(pduSession.getSessionCount());
		pduSessionEntity.setUpdatedTime(pduSession.getUpdatedTime());
		return pduSessionEntity;
	}

	public static Site siteEntityToModel(SiteEntity siteEntity) {
		Site site = new Site();
		site.setSiteId(siteEntity.getId());
		site.setSiteName(siteEntity.getName());
		return site;
	}

	public static List<Site> siteEntityToModelList(List<SiteEntity> siteEntityList) {
		List<Site> siteList = new ArrayList<Site>();
		for (SiteEntity siteEntity : siteEntityList) {
			siteList.add(siteEntityToModel(siteEntity));
		}
		return siteList;
	}

	public static SiteEntity siteModelToEntity(Site site) {
		SiteEntity siteEntity = new SiteEntity();
		siteEntity.setId(site.getSiteId());
		siteEntity.setName(site.getSiteName());
		return siteEntity;
	}

	public static Subscriber subscriberEntityToModel(SubscriberEntity subscriberEntity) {
		Subscriber subscriber = new Subscriber();
		subscriber.setImei(subscriberEntity.getImei());
		subscriber.setImsi(subscriberEntity.getImsi());
		subscriber.setIpAddress(subscriber.getIpAddress());
		subscriber.setMsisdn(subscriberEntity.getMsisdn());
		subscriber.setName(subscriberEntity.getName());
		subscriber.setStatus(subscriberEntity.getStatus());
		subscriber.setSubscriberId(subscriberEntity.getId());
		return subscriber;
	}

	public static List<Subscriber> subscriberEntityToModelList(List<SubscriberEntity> subscriberEntityList) {
		List<Subscriber> subscriberList = new ArrayList<Subscriber>();
		for (SubscriberEntity subscriberEntity : subscriberEntityList) {
			subscriberList.add(subscriberEntityToModel(subscriberEntity));
		}
		return subscriberList;
	}

	public static SubscriberEntity subscriberModelToEntity(Subscriber subscriber) {
		SubscriberEntity subscriberEntity = new SubscriberEntity();
		subscriberEntity.setImei(subscriber.getImei());
		subscriberEntity.setImsi(subscriber.getImsi());
		subscriberEntity.setIpAddress(subscriber.getIpAddress());
		subscriberEntity.setMsisdn(subscriber.getMsisdn());
		subscriberEntity.setName(subscriber.getName());
		subscriberEntity.setStatus(subscriber.getStatus());
		subscriberEntity.setId(subscriber.getSubscriberId());
		return subscriberEntity;
	}

	public static Throughput throughputEntityToModel(ThroughputEntity throughputEntity) {
		Throughput throughput = new Throughput();
		throughput.setDownlink(throughputEntity.getDownlink());
		throughput.setUplink(throughputEntity.getUplink());
		throughput.setUpdatedTime(throughputEntity.getUpdatedTime());
		throughput.setId(throughputEntity.getId());
		return throughput;
	}

	public static List<Throughput> throughputEntityToModelList(List<ThroughputEntity> throughputEntityList) {
		List<Throughput> throughputList = new ArrayList<Throughput>();
		for (ThroughputEntity throughputEntity : throughputEntityList) {
			throughputList.add(throughputEntityToModel(throughputEntity));
		}
		return throughputList;
	}

	public static ThroughputEntity subscriberModelToEntity(Throughput throughput) {
		ThroughputEntity throughputEntity = new ThroughputEntity();
		throughputEntity.setDownlink(throughput.getDownlink());
		throughputEntity.setUplink(throughput.getUplink());
		throughputEntity.setUpdatedTime(throughput.getUpdatedTime());
		throughputEntity.setId(throughput.getId());
		return throughputEntity;
	}
}
