package com.dms.datamodelmanagementserver.authorization.group.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthUpdateRequestDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationBySubjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationDetailDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupMemberDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.SearchForGroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.UserSearchRequestDTO;
import com.dms.datamodelmanagementserver.authorization.group.mapper.GroupAuthorizationMapper;
import com.dms.datamodelmanagementserver.authorization.group.service.GroupAuthorizationService;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;

@Service
public class GroupAuthorizationServiceImpl implements GroupAuthorizationService{
	
	private final GroupAuthorizationMapper groupAuthorizationMapper;

	public GroupAuthorizationServiceImpl(GroupAuthorizationMapper groupAuthorizationMapper) {
		this.groupAuthorizationMapper = groupAuthorizationMapper;
	}
	

	@Override
	public boolean insertIndividualDetailAndAuthorization(GroupAuthorizationDetailDTO groupAuthorizationDetailDTO) {
		boolean insertResultOfGroup = groupAuthorizationMapper.insertGroup(groupAuthorizationDetailDTO.getGroupDTO());
		boolean insertResultOfGroupAuth = groupAuthorizationMapper.insertGroupAuth(groupAuthorizationDetailDTO.getGroupAuthorizationBySubjAreaIdDtoList());
		if(insertResultOfGroup && insertResultOfGroupAuth) {
			return true;
		}
		return false;
	}

	@Override
	public List<GroupDTO> getAllGroupList() {
		return groupAuthorizationMapper.getAllGroupList();
	}

	@Override
	public GroupAuthorizationDetailDTO findGroupAuthorizationDetailById(String groupId) {
		GroupDTO groupDTO = groupAuthorizationMapper.findGroupByGroupId(groupId);
		List<GroupAuthorizationBySubjAreaIdDTO> GroupAuthorizationBySubjAreaIdList = groupAuthorizationMapper.findGroupAuthorizationByStdjAreaId(groupId);
		List<GroupMemberDTO> groupMemberList = groupAuthorizationMapper.findAllMemberByGroupId(groupId);
		return GroupAuthorizationDetailDTO.builder()
				.groupDTO(groupDTO)
				.groupAuthorizationBySubjAreaIdDtoList(GroupAuthorizationBySubjAreaIdList)
				.groupMemberDtoList(groupMemberList)
				.build();
	}
	
	@Override
	public boolean updateGroupAuthorization(GroupAuthUpdateRequestDTO groupAuthUpdateRequestDTO) {
		String groupId = groupAuthUpdateRequestDTO.getGroupId();
		
		List<GroupAuthorizationBySubjAreaIdDTO> subjAreaIdList = groupAuthorizationMapper.findSubjAreaIdByGroupId(groupId);
		List<GroupAuthorizationBySubjAreaIdDTO> GroupAuthorizationBySubjAreaIdDTOList = groupAuthUpdateRequestDTO.getGroupAuthorizationBySubjAreaIdDTOList();
		
		
		if(!CollectionUtils.isEmpty(subjAreaIdList) && CollectionUtils.isEmpty(GroupAuthorizationBySubjAreaIdDTOList)) {
			return subjAreaIdList.stream().map(it -> groupAuthorizationMapper.deleteGroupAuthBySubjAreaId(it.getSubjAreaId(), it.getGroupId())).allMatch(it -> it);

		}
		
		if (!CollectionUtils.isEmpty(subjAreaIdList)) {
			List<String> updatedSubjAreaIdList = GroupAuthorizationBySubjAreaIdDTOList.stream().map(dto -> dto.getSubjAreaId()).toList();

			subjAreaIdList.stream()
			.filter(it -> !updatedSubjAreaIdList.contains(it.getSubjAreaId()))
			.forEach(it -> groupAuthorizationMapper.deleteGroupAuthBySubjAreaId(it.getSubjAreaId(), it.getGroupId()));	
		}
		
		return groupAuthUpdateRequestDTO.getGroupAuthorizationBySubjAreaIdDTOList()
				.stream()
				.map(dto -> {
					String authTpCd = groupAuthorizationMapper.findAuthByGroupIdAndSubjAreaId(dto.getSubjAreaId(), dto.getGroupId());
					if(StringUtils.hasText(authTpCd)) {
						return groupAuthorizationMapper.updateAuthByGroupIdAndSubjAreaId(dto);
					}
					return groupAuthorizationMapper.insertGroupAuthOneByOne(dto);
				})
				.allMatch(it -> it);
	}
	
	@Override
	public boolean validateGroupId(String groupId) {
		return groupAuthorizationMapper.validateGroupId(groupId);
	}

	@Override
	public boolean validateGroupName(String groupName) {
		return groupAuthorizationMapper.validateGroupName(groupName);
	}

	@Override
	public List<IndividualUserDTO> searchIndividualByName(UserSearchRequestDTO userSearchRequestDTO) {
		return groupAuthorizationMapper.searchIndividualByName(userSearchRequestDTO);
	}

	@Override
	public List<GroupDTO> findGroupBySearchCondition(SearchForGroupDTO searchForGroupDTO) {
		return groupAuthorizationMapper.findGroupBySearchCondition(searchForGroupDTO);
	}

	@Override
	public boolean updateGroup(GroupDTO groupDTO) {
		return groupAuthorizationMapper.updateGroup(groupDTO);
	}
	
	@Override
	public boolean insertIndividualToGroup(UserSearchRequestDTO userSearchRequestDTO) {
		return groupAuthorizationMapper.insertIndividualToGroup(userSearchRequestDTO);
	}

	@Override
	public boolean deleteIndividualFromGroup(UserSearchRequestDTO userSearchRequestDTO) {
		return groupAuthorizationMapper.deleteIndividualFromGroup(userSearchRequestDTO);
	}


}
