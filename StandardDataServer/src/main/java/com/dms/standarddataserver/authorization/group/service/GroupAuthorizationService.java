package com.dms.standarddataserver.authorization.group.service;

import java.util.List;

import com.dms.standarddataserver.authorization.group.dto.GroupAuthorizationDetailDTO;
import com.dms.standarddataserver.authorization.group.dto.GroupDTO;

public interface GroupAuthorizationService {
	public List<GroupDTO> getAllGroupList();
	public GroupAuthorizationDetailDTO findGroupAuthorizationDetailById(String groupId);
}
