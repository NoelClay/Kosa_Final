package com.dms.standarddataserver.authorization.group.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dms.standarddataserver.authorization.group.dto.GroupAuthorizationBySubjAreaIdDTO;
import com.dms.standarddataserver.authorization.group.dto.GroupAuthorizationDetailDTO;
import com.dms.standarddataserver.authorization.group.dto.GroupDTO;
import com.dms.standarddataserver.authorization.group.dto.GroupMemberDTO;
import com.dms.standarddataserver.authorization.group.mapper.GroupAuthorizationMapper;
import com.dms.standarddataserver.authorization.group.service.GroupAuthorizationService;

@Service
public class GroupAuthorizationServiceImpl implements GroupAuthorizationService{
	
	private final GroupAuthorizationMapper groupAuthorizationMapper;

	public GroupAuthorizationServiceImpl(GroupAuthorizationMapper groupAuthorizationMapper) {
		this.groupAuthorizationMapper = groupAuthorizationMapper;
	}

	@Override
	public List<GroupDTO> getAllGroupList() {
		return groupAuthorizationMapper.getAllGroupList();
	}

	@Override
	public GroupAuthorizationDetailDTO findGroupAuthorizationDetailById(String groupId) {
		List<GroupAuthorizationBySubjAreaIdDTO> GroupAuthorizationBySubjAreaIdList = groupAuthorizationMapper.findGroupAuthorizationByStdjAreaId(groupId);
		List<GroupMemberDTO> groupMemberLsit = groupAuthorizationMapper.findAllMemberByGroupId(groupId);
		return GroupAuthorizationDetailDTO.builder()
				.groupAuthorizationBySubjAreaIdDtoList(GroupAuthorizationBySubjAreaIdList)
				.groupMemberDtoList(groupMemberLsit)
				.build();
	}

}
