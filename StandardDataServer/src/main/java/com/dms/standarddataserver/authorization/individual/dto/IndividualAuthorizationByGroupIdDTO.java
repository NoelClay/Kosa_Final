package com.dms.standarddataserver.authorization.individual.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualAuthorizationByGroupIdDTO {
	private String subjAreaId;
	private String subjAreaName;
	private String groupId;
	private String groupName;
	private String authTpCd;
}
