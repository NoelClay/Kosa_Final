package com.dms.standarddataserver.term.dto;

import com.dms.standarddataserver.word.dto.WordDTO;
import lombok.Data;

import java.util.List;

@Data
public class TermWordDTO {
    private WordDTO wordDTO;
    private List<TermDTO> termDTOList;
}
