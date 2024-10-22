package com.dms.datamodelmanagementserver.authorization.individual.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchForUserDTO {
	private String searchCondition;
	private String searchValue;
}
