package com.dms.datamodelmanagementserver.authorization.individual.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationBySubjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthUpdateRequestDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthorizationByStdjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthorizationDetailDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SubjAreaDetailDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.UpdateUserPasswordDTO;
import com.dms.datamodelmanagementserver.authorization.individual.mapper.IndividualAuthorizationMapper;
import com.dms.datamodelmanagementserver.authorization.individual.service.IndividualAuthorizationService;

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
		individualUserDTO.setGroupDTOList(individualAuthorizationMapper.findGroupByUserId(usrId)); 
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
	public boolean updateIndividualAuthorization(IndividualAuthUpdateRequestDTO individualAuthUpdateRequestDTO) {
		String usrId = individualAuthUpdateRequestDTO.getUsrId();
		
		List<IndividualAuthorizationByStdjAreaIdDTO> subjAreaIdList = individualAuthorizationMapper.findSubjAreaIdByUsrId(usrId);
		List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList = individualAuthUpdateRequestDTO.getIndividualAuthorizationByStdjAreaIdDTOList();
		
		if(!CollectionUtils.isEmpty(subjAreaIdList) && CollectionUtils.isEmpty(individualAuthorizationByStdjAreaIdDTOList)) {
			return subjAreaIdList.stream().map(it -> individualAuthorizationMapper.deleteIndividualAuthBySubjAreaId(it.getSubjAreaId(), it.getUsrId())).allMatch(it -> it);
		}
		
		if (!CollectionUtils.isEmpty(subjAreaIdList)) {
			List<String> updatedSubjAreaIdList = individualAuthorizationByStdjAreaIdDTOList.stream().map(dto -> dto.getSubjAreaId()).toList();

			subjAreaIdList.stream()
			.filter(it -> !updatedSubjAreaIdList.contains(it.getSubjAreaId()))
			.forEach(it -> individualAuthorizationMapper.deleteIndividualAuthBySubjAreaId(it.getSubjAreaId(), it.getUsrId()));	
		}
		
		return individualAuthorizationByStdjAreaIdDTOList
				.stream()
				.map(dto -> {
					String authTpCd = individualAuthorizationMapper.findIndividualIdAndSubjAreaId(dto.getSubjAreaId(), dto.getUsrId());
					if(StringUtils.hasText(authTpCd)) {
						return individualAuthorizationMapper.updateIndividualAuthorization(dto);
					}
					return individualAuthorizationMapper.insertIndividualAuthOneByOne(dto);
				})
				.allMatch(it -> it);
	}

	@Override
	public List<IndividualUserDTO> getAllUsersBySearch(SearchForUserDTO searchForUserDTO) {
		return individualAuthorizationMapper.getAllUsersBySearch(searchForUserDTO);
	}
	
	@Override
	public boolean validateUserId(String userId) {
		return individualAuthorizationMapper.validateUserId(userId);
	}
	
	@Override
	public boolean updateUser(IndividualUserDTO individualUserDTO) {
		return individualAuthorizationMapper.updateUser(individualUserDTO);
	}

	@Override
	public IndividualUserDTO findIndividualUserById(String userId) {
		return individualAuthorizationMapper.findIndividualUserById(userId);
	}

	@Override
	public boolean updateUserPassword(UpdateUserPasswordDTO updateUserPasswordDTO) {
		boolean isCorrect = individualAuthorizationMapper.validateUserPassword(updateUserPasswordDTO);
		if(isCorrect) {
			return individualAuthorizationMapper.updateUserPassword(updateUserPasswordDTO);
		}
		return false;
	}
	
}
