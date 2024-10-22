package com.dms.standarddataserver.authorization.individual.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationByStdjAreaIdDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationDetailDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SubjAreaDetailDTO;
import com.dms.standarddataserver.authorization.individual.mapper.IndividualAuthorizationMapper;
import com.dms.standarddataserver.authorization.individual.service.IndividualAuthorizationService;

@Service
public class IndividualAuthorizationServiceImpl implements IndividualAuthorizationService{
	
	private final IndividualAuthorizationMapper individualAuthorizationMapper;

	public IndividualAuthorizationServiceImpl(IndividualAuthorizationMapper individualAuthorizationMapper) {
		this.individualAuthorizationMapper = individualAuthorizationMapper;
	}

	@Override
	public List<IndividualUserDTO> getAllIndividualAuthorizationList() {
		return individualAuthorizationMapper.getAllIndividualAuthorizationList();
	}

	@Override
	public IndividualAuthorizationDetailDTO findIndividualAuthorizationDetailById(String usrId) {
		IndividualUserDTO individualUserDTO = individualAuthorizationMapper.findIndividualUserById(usrId);
		List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdList = individualAuthorizationMapper.findIndividualAuthorizationByStdjAreaId(usrId);
//		List<IndividualAuthorizationByGroupIdDTO> individualAuthorizationByGroupIdList = individualAuthorizationMapper.findIndividualAuthorizationByGroupId(individualUserDTO.getGroupId());
		return IndividualAuthorizationDetailDTO.builder()
				.individualUserDTO(individualUserDTO)
				.individualAuthorizationByStdjAreaIdDTOList(individualAuthorizationByStdjAreaIdList)
				.build();
	}
	
	@Override
	public boolean insertIndividualDetailAndAuthorization(IndividualAuthorizationDetailDTO individualAuthorizationDetailDTO) {
		boolean insertResultOfIndividual = individualAuthorizationMapper.insertIndividual(individualAuthorizationDetailDTO.getIndividualUserDTO());
		boolean insertResultOfIndividualAuth = individualAuthorizationMapper.insertIndividualAuth(individualAuthorizationDetailDTO.getIndividualAuthorizationByStdjAreaIdDTOList());
		if (insertResultOfIndividual && insertResultOfIndividualAuth) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<SubjAreaDetailDTO> getAllSubjAreaList() {
		return individualAuthorizationMapper.getAllSubjAreaList();
	}

	@Override
	public boolean updateIndividualAuthorization(List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList) {
		List<Boolean> resultList = new ArrayList<>();
		individualAuthorizationByStdjAreaIdDTOList.stream().forEach(it -> {
			String authTpCd = individualAuthorizationMapper.findIndividualIdAndSubjAreaId(it.getSubjAreaId(), it.getUsrId());
			if (authTpCd != null) {
				resultList.add(individualAuthorizationMapper.updateIndividualAuthorization(it));
			} else {
				resultList.add(individualAuthorizationMapper.insertIndividualAuthOneByOne(it));
			}
		});
		return resultList.stream().allMatch(it -> it == true);
	}

	@Override
	public List<IndividualUserDTO> getAllUsersBySearch(SearchForUserDTO searchForUserDTO) {
		return individualAuthorizationMapper.getAllUsersBySearch(searchForUserDTO);
	}
	
	public boolean validateUserId(String userId) {
		return individualAuthorizationMapper.validateUserId(userId);
	}
	
	@Override
	public boolean updateUser(IndividualUserDTO individualUserDTO) {
		return individualAuthorizationMapper.updateUser(individualUserDTO);
	}

//	@Override
//	public boolean deleteUser(String userId) {
//		return individualAuthorizationMapper.deleteUser(userId);
//	}
}
