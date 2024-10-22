package com.dms.datamodelmanagementserver.authorization.group.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMemberDTO {
	private String groupName;
	private String usrId;
	private String usrName;
}
