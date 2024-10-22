package com.dms.standarddataserver.collectionBook.service;

import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataSearchDTO;

import java.util.List;

public interface CollectionBookDisplayService {
    public List<StandardDataDTO> searchCollection(StandardDataSearchDTO standardDataSearchDTO);
}
