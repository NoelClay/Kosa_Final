package com.dms.datamodelmanagementserver.authorization.group.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequestDTO {
	private String targetUserId;
	private String targetUserName;
	private String targetGroupId;
}
