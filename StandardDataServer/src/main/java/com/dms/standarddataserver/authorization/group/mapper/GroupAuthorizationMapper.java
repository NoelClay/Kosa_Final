package com.dms.standarddataserver.authorization.group.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dms.standarddataserver.authorization.group.dto.GroupAuthorizationBySubjAreaIdDTO;
import com.dms.standarddataserver.authorization.group.dto.GroupDTO;
import com.dms.standarddataserver.authorization.group.dto.GroupMemberDTO;

@Mapper
public interface GroupAuthorizationMapper {
	List<GroupDTO> getAllGroupList();
	List<GroupAuthorizationBySubjAreaIdDTO> findGroupAuthorizationByStdjAreaId(String groupId);
	List<GroupMemberDTO> findAllMemberByGroupId(String groupId);
}
