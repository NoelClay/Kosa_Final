package com.dms.datamodelmanagementserver.single.term.service;

import com.dms.datamodelmanagementserver.single.term.dto.TermDomainDTO;

public interface TermUpdateService {

    boolean updateTermRest(String dicId, String domId, String dicDesc);


}
