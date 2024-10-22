package com.dms.standarddataserver.standardData.service;


import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataSearchDTO;

import java.util.List;

public interface StandardDataSearchService {
    List<StandardDataDTO> search(StandardDataSearchDTO standardDataSearchDTO);
}
