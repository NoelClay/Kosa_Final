package com.dms.standarddataserver.authorization.group.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupAuthorizationDetailDTO {
	private List<GroupAuthorizationBySubjAreaIdDTO> groupAuthorizationBySubjAreaIdDtoList;
	private List<GroupMemberDTO> groupMemberDtoList;
}
