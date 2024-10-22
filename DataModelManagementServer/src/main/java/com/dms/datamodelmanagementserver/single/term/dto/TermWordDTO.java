package com.dms.datamodelmanagementserver.single.term.dto;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import lombok.Data;

import java.util.List;

@Data
public class TermWordDTO {
    private WordDTO wordDTO;
    private List<TermDTO> termDTOList;
}
