package com.dms.datamodelmanagementserver.standardData.service;

import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataSearchDTO;

import java.util.List;

public interface StandardDataSearchService {
    List<StandardDataDTO> search(StandardDataSearchDTO standardDataSearchDTO);
}
