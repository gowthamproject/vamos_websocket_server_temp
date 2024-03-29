package com.wipro.vamos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Constant;
import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.SubscriberEntity;
import com.wipro.vamos.exception.ResourceNotFoundException;
import com.wipro.vamos.model.Subscriber;
import com.wipro.vamos.repository.SubscriberRepository;
import com.wipro.vamos.response.SubscriberResponse;

@Service
public class SubscriberService {

	@Autowired
	private SubscriberRepository subscriberRepository;

	public List<Subscriber> getAllSubscriber() {
		return Mapper.subscriberEntityToModelList(subscriberRepository.findAll());
	}

	public Subscriber getSubscriberByID(long subscriber_id) throws ResourceNotFoundException {
		Optional<SubscriberEntity> subscriberOptional = subscriberRepository.findById(subscriber_id);
		if (!subscriberOptional.isPresent())
			throw new ResourceNotFoundException("Subscriber not found for this id :: " + subscriber_id);
		return Mapper.subscriberEntityToModel(subscriberOptional.get());

	}

	public List<Subscriber> getSubscriberByCoreID(String core_id) throws ResourceNotFoundException {
		return Mapper.subscriberEntityToModelList(subscriberRepository.findByCore5GId(core_id));
	}

	public SubscriberResponse getSubscriberStatusCountByCoreId(String core_id) {
		List<Subscriber> subscriberList = Mapper
				.subscriberEntityToModelList(subscriberRepository.findByCore5GId(core_id));
		Map<String, Long> subscriberCountByStatusMap = subscriberList.stream()
				.collect(Collectors.groupingBy(Subscriber::getStatus, Collectors.counting()));
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		subscriberResponse.setCore_id(core_id);

		if (subscriberCountByStatusMap == null)
			subscriberCountByStatusMap = new HashMap<String, Long>();

		if (subscriberCountByStatusMap.get(Constant.CONNECTED) == null)
			subscriberCountByStatusMap.put(Constant.CONNECTED, 0l);
		if (subscriberCountByStatusMap.get(Constant.DISCONNECTED) == null)
			subscriberCountByStatusMap.put(Constant.DISCONNECTED, 0l);

		subscriberResponse.setSubscriberCountByStatus(subscriberCountByStatusMap);
		return subscriberResponse;
	}

	public void saveSubscriber(String core_id, Subscriber subscriber) {

		SubscriberEntity subscriberEntity = Mapper.subscriberModelToEntity(subscriber);
		Core5GEntity core5gEntity = new Core5GEntity();
		core5gEntity.setId(core_id);
		subscriberEntity.setCore5G(core5gEntity);
		subscriberRepository.save(subscriberEntity);
	}

	public void deleteGNodeBs(Long subscriber_id) {
		subscriberRepository.deleteById(subscriber_id);
	}

}
