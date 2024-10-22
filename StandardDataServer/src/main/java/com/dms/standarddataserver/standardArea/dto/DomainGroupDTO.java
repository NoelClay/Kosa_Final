package com.dms.standarddataserver.standardArea.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DomainGroupDTO {
	private String domainGroupId;
	private String subjAreaId;
	private String domainGroupName;
	private String domainGroupDesc;
	private String createUserId;
}
