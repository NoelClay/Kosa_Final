package com.dms.datamodelmanagementserver.synonym.service;

import com.dms.datamodelmanagementserver.synonym.dto.SynonymDTO;

public interface  SynonymDuplicateService {

    public boolean isDuplicateSynonymRest(SynonymDTO synonymDTO);


}
