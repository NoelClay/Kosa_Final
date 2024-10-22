package com.dms.datamodelmanagementserver.authorization.individual.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserPasswordDTO {
	private String usrId;
	private String usrCurrentPassword;
	private String usrUpdatePassword;
}
