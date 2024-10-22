package com.dms.datamodelmanagementserver.authorization.group.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchForGroupDTO {
	private String searchCondition;
	private String searchValue;
}
