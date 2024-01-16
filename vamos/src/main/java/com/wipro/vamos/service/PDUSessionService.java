package com.wipro.vamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.Core5GEntity;
import com.wipro.vamos.entity.GNodeBEntity;
import com.wipro.vamos.entity.PDUSessionEntity;
import com.wipro.vamos.model.PDUSession;
import com.wipro.vamos.repository.PDUSessionRepository;

@Service
public class PDUSessionService {

	@Autowired
	PDUSessionRepository pduSessionRepository;

	public PDUSession getPDUSessionStatusByCoreId(String core_id) {
		List<PDUSessionEntity> pudSessionEntities = pduSessionRepository.findAllByCore5GId(core_id);
		if (pudSessionEntities == null || pudSessionEntities.isEmpty())
			return new PDUSession();
		return Mapper.pduSessionEntityToModel(pduSessionRepository.findAllByCore5GId(core_id).get(0));
	}

	public void savePduSession(String core_id, PDUSession pduSession) {
		PDUSessionEntity pduSessionEntity = Mapper.pduSessionModelToEntity(pduSession);
		Core5GEntity core5gEntity = new Core5GEntity();
		core5gEntity.setId(core_id);
		pduSessionEntity.setCore5G(core5gEntity);

		GNodeBEntity gNodeBEntity = new GNodeBEntity();
		gNodeBEntity.setId(pduSession.getGnbId());
		pduSessionEntity.setServingGnbId(gNodeBEntity);
		pduSessionRepository.save(pduSessionEntity);
	}

}
