package com.dms.standarddataserver.authorization.individual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualAuthorizationByStdjAreaIdDTO {
	private String subjAreaId;
	private String subjAreaName;
	private String authTpCd;
	private String usrId;
}
