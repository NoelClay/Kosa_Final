package com.dms.standarddataserver.authorization.individual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualUserDTO {
	private String usrId;
	private String usrName;
	private String usrPassword;
	private String useYn;
	private String usrEmail;
	private String groupId;
	private String groupName;
}
