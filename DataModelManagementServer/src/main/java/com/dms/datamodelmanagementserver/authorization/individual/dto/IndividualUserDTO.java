package com.dms.datamodelmanagementserver.authorization.individual.dto;

import java.util.List;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualUserDTO {
	private String usrId;
	private String usrName;
	private String usrPassword;
	private String useYn;
	private String usrEmail;
	private List<GroupDTO> groupDTOList;
	private String groupId;
	private String groupName;
}
