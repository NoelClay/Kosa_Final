package com.dms.datamodelmanagementserver.single.term.dto;

import lombok.Data;

@Data
public class TermDTO {

	private String subjAreaId;
    private String termId;
    private int orderNo;
    private String dicId;
    private String avalStDt;
    private String avalEndDt;

}
