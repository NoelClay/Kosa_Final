package com.dms.datamodelmanagementserver.single.term.service;


import com.dms.datamodelmanagementserver.single.term.dto.TermDTO;

import java.util.List;

public interface TermInsertService {

    boolean singleTermInsertRest(List<TermDTO> termDTOList);


}
