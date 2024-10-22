package com.dms.datamodelmanagementserver.authorization.group.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationBySubjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupMemberDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.SearchForGroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.UserSearchRequestDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;

@Mapper
public interface GroupAuthorizationMapper {
	public boolean insertGroup(GroupDTO groupDTO);
	public boolean insertGroupAuth(List<GroupAuthorizationBySubjAreaIdDTO> groupAuthorizationBySubjAreaIdDtoList);
	public List<GroupDTO> getAllGroupList();
	public GroupDTO findGroupByGroupId(String groupId);
	public List<GroupAuthorizationBySubjAreaIdDTO> findGroupAuthorizationByStdjAreaId(String groupId);
	public List<GroupAuthorizationBySubjAreaIdDTO> findSubjAreaIdByGroupId(String groupId);
	public List<GroupMemberDTO> findAllMemberByGroupId(String groupId);
	public boolean insertGroupAuthOneByOne(GroupAuthorizationBySubjAreaIdDTO groupAuthorizationBySubjAreaIdDTO);
	public String findAuthByGroupIdAndSubjAreaId(@Param("subjAreaId")String subjAreaId, @Param("groupId")String groupId);
	public boolean updateAuthByGroupIdAndSubjAreaId(GroupAuthorizationBySubjAreaIdDTO groupAuthorizationBySubjAreaIdDTO);
	public boolean validateGroupId(String groupId);
	public boolean validateGroupName(String groupName);
	public List<IndividualUserDTO> searchIndividualByName(UserSearchRequestDTO userSearchRequestDTO);
	public boolean deleteGroupAuthBySubjAreaId(@Param("subjAreaId")String subjAreaId, @Param("groupId")String groupId);
	public List<GroupDTO> findGroupBySearchCondition(SearchForGroupDTO searchForGroupDTO);
	public boolean updateGroup(GroupDTO groupDTO);
	public boolean insertIndividualToGroup(UserSearchRequestDTO userSearchRequestDTO);
	public boolean deleteIndividualFromGroup(UserSearchRequestDTO userSearchRequestDTO);
}
