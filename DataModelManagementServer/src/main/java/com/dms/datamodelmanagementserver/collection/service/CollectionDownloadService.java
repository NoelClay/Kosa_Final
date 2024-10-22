package com.dms.datamodelmanagementserver.collection.service;

import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface CollectionDownloadService {

    public String saveCollectionDataToExcel(List<StandardDataDTO> standardDataDTOList, HttpServletResponse response) throws IOException;
}
