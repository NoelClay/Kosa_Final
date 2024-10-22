package com.dms.datamodelmanagementserver.collectionBook.service;

import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataSearchDTO;

import java.util.List;

public interface CollectionBookDisplayService {

    public List<StandardDataDTO> searchCollection(StandardDataSearchDTO standardDataSearchDTO);
}
