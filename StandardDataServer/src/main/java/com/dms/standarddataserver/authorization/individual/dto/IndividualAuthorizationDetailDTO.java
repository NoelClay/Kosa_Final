package com.dms.standarddataserver.authorization.individual.dto;

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
public class IndividualAuthorizationDetailDTO {
	private IndividualUserDTO individualUserDTO;
	private List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList;
	private List<IndividualAuthorizationByGroupIdDTO> individualAuthorizationByGroupIdDTOList;
}
