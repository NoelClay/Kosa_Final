package com.dms.standarddataserver.authorization.individual.service;

import java.util.List;

import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationByStdjAreaIdDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationDetailDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SubjAreaDetailDTO;

public interface IndividualAuthorizationService {
	public List<IndividualUserDTO> getAllIndividualAuthorizationList();
	public IndividualAuthorizationDetailDTO findIndividualAuthorizationDetailById(String usrId);
	public boolean insertIndividualDetailAndAuthorization(IndividualAuthorizationDetailDTO individualAuthorizationDetailDTO);
	public List<SubjAreaDetailDTO> getAllSubjAreaList();
	public boolean updateIndividualAuthorization(List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList);
	public List<IndividualUserDTO> getAllUsersBySearch(SearchForUserDTO searchForUserDTO);
	public boolean validateUserId(String userId);
	public boolean updateUser(IndividualUserDTO individualUserDTO);
//	public boolean deleteUser(String userId);
}
