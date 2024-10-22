package com.dms.standarddataserver.standardData.service;

import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDictionaryDTO;

import java.util.List;

public interface StandardDataCheckSynonymService {
    StandardDataDTO checkSynonym(StandardDataDTO standardDataDTO);
}
