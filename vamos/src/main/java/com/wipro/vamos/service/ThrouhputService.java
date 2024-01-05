package com.wipro.vamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.ThroughputEntity;
import com.wipro.vamos.model.Throughput;
import com.wipro.vamos.repository.ThroughputRepository;

@Service
public class ThrouhputService {
	@Autowired
	ThroughputRepository throughputRepository;

	public List<Throughput> getThrouhputsByCoreId(String core_id) {
		return Mapper.throughputEntityToModelList(throughputRepository.findAllByCore5GId(core_id));
	}

	public Throughput getThrouhputByCoreId(String core_id) {
		return Mapper.throughputEntityToModel(throughputRepository.findAllByCore5GId(core_id).get(0));
	}

	public void saveThroughput(String core_id, Throughput throughput) {
		ThroughputEntity throuhputEntity = Mapper.subscriberModelToEntity(throughput);
		Core5GEntity core5gEntity = new Core5GEntity();
		core5gEntity.setId(core_id);
		throuhputEntity.setCore5G(core5gEntity);
		throughputRepository.save(throuhputEntity);
	}
}
