package com.dms.standarddataserver.bulk.term.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordOfTermDTO {

	private String stdAreaId;
    private String termId;
    private Integer orderNum;
    private String dicId;
}
