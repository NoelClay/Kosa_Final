package com.dms.datamodelmanagementserver.authorization.group.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupAuthorizationBySubjAreaIdDTO {
	private String subjAreaId;
	private String subjAreaName;
	private String groupId;
	private String groupName;
	private String authTpCd;
}
