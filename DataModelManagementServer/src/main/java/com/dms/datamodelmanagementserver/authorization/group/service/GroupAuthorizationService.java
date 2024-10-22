package com.dms.datamodelmanagementserver.authorization.group.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthUpdateRequestDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationBySubjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationDetailDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.SearchForGroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.UserSearchRequestDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;

public interface GroupAuthorizationService {
	public boolean insertIndividualDetailAndAuthorization(GroupAuthorizationDetailDTO groupAuthorizationDetailDTO);
	public List<GroupDTO> getAllGroupList();
	public GroupAuthorizationDetailDTO findGroupAuthorizationDetailById(String groupId);
	public boolean updateGroupAuthorization(GroupAuthUpdateRequestDTO groupAuthUpdateRequestDTO);
	public boolean validateGroupId(String groupId);
	public boolean validateGroupName(String groupName);
	public List<IndividualUserDTO> searchIndividualByName(UserSearchRequestDTO userSearchRequestDTO);
	public List<GroupDTO> findGroupBySearchCondition(SearchForGroupDTO searchForGroupDTO);
	public boolean updateGroup(@RequestBody GroupDTO groupDTO);
	public boolean insertIndividualToGroup(UserSearchRequestDTO userSearchRequestDTO);
	public boolean deleteIndividualFromGroup(UserSearchRequestDTO userSearchRequestDTO);
}
