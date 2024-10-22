package com.dms.datamodelmanagementserver.authorization.individual.service;

import java.util.List;

import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthUpdateRequestDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthorizationDetailDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SubjAreaDetailDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.UpdateUserPasswordDTO;


public interface IndividualAuthorizationService {
	public List<IndividualUserDTO> getAllIndividualAuthorizationList();
	public IndividualAuthorizationDetailDTO findIndividualAuthorizationDetailById(String usrId);
	public boolean insertIndividualDetailAndAuthorization(IndividualAuthorizationDetailDTO individualAuthorizationDetailDTO);
	public List<SubjAreaDetailDTO> getAllSubjAreaList();
	public boolean updateIndividualAuthorization(IndividualAuthUpdateRequestDTO individualAuthUpdateRequestDTO);
	public List<IndividualUserDTO> getAllUsersBySearch(SearchForUserDTO searchForUserDTO);
	public boolean validateUserId(String userId);
	boolean updateUser(IndividualUserDTO individualUserDTO);
	public IndividualUserDTO findIndividualUserById(String userId);
	public boolean updateUserPassword(UpdateUserPasswordDTO updateUserPasswordDTO);
}
