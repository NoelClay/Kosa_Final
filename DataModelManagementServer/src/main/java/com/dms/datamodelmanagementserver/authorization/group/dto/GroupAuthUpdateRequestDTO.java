package com.dms.datamodelmanagementserver.authorization.group.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupAuthUpdateRequestDTO {
	private String groupId;
	private List<GroupAuthorizationBySubjAreaIdDTO> groupAuthorizationBySubjAreaIdDTOList;
}
