package com.dms.datamodelmanagementserver.authorization.group.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupAuthorizationDetailDTO {
	private GroupDTO groupDTO; 
	private List<GroupAuthorizationBySubjAreaIdDTO> groupAuthorizationBySubjAreaIdDtoList;
	private List<GroupMemberDTO> groupMemberDtoList;
}
