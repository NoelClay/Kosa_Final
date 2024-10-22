package com.dms.datamodelmanagementserver.authorization.individual.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualAuthUpdateRequestDTO {
	private String usrId;
	private List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList;
}
