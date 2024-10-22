package com.dms.datamodelmanagementserver.collectionBook.service;

import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface CollectionBookDownloadService {

    public String saveCollectionBookDataToExcel(List<StandardDataDTO> standardDataDTOList, HttpServletResponse response) throws IOException;
}
