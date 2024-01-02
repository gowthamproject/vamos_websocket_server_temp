package com.wipro.vamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.vamos.common.Mapper;
import com.wipro.vamos.entity.PDUSessionEntity;
import com.wipro.vamos.model.PDUSession;
import com.wipro.vamos.repository.PDUSessionRepository;

@Service
public class PDUSessionService {

	@Autowired
	PDUSessionRepository pduSessionRepository;

	public PDUSession getPDUSessionStatusByCoreId(String core_id) {
		List<PDUSessionEntity> pudSessionEntities = pduSessionRepository.findAllByCore5GId(core_id);
		if(pudSessionEntities == null)
			return new PDUSession();
		return Mapper.pduSessionEntityToModel(pduSessionRepository.findAllByCore5GId(core_id).get(0));
	}

	public void savePduSession(@RequestBody PDUSession pduSession) {
		pduSessionRepository.save(Mapper.pduSessionModelToEntity(pduSession));
	}

}
