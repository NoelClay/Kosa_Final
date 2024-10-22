package com.dms.datamodelmanagementserver.bulk.word.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.word.dto.BulkWordDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.service.BulkWordInsertService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BulkWordInsertServiceImpl implements BulkWordInsertService {

    private final ApiRequestBuilder<List<BulkWordDataDTO>> apiRequestBuilder;

    @Autowired
    public BulkWordInsertServiceImpl(ApiRequestBuilder<List<BulkWordDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<BulkWordDataDTO> insertBulkWord(List<BulkWordDataDTO> bulkWordDataDTOList) {
        return apiRequestBuilder.setUrl("/std/bulk-word/insert")
                .setObject(bulkWordDataDTOList)
                .setResponseType(new ParameterizedTypeReference<List<BulkWordDataDTO>>() {})
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
