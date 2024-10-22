package com.dms.datamodelmanagementserver.authorization.individual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjAreaDetailDTO {
	private String subjAreaId;
	private String subjAreaName;
	private String subjAreaDesc;
}