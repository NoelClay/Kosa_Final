package com.dms.datamodelmanagementserver.bulk.term.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermInsertService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BulkTermInsertServiceImpl implements BulkTermInsertService {

    private final ApiRequestBuilder<List<BulkTermDataDTO>> apiRequestBuilder;

    public BulkTermInsertServiceImpl(ApiRequestBuilder<List<BulkTermDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<BulkTermDataDTO> insertBulkTerm(List<BulkTermDataDTO> bulkTermDataDTOList) {
        return apiRequestBuilder.setUrl("/std/bulk-term/insert")
                .setObject(bulkTermDataDTOList)
                .setResponseType(new ParameterizedTypeReference<List<BulkTermDataDTO>>() {})
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
