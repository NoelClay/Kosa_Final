package com.dms.datamodelmanagementserver.standardData.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataSearchDTO;
import com.dms.datamodelmanagementserver.standardData.service.StandardDataSearchService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardDataSearchServiceImpl implements StandardDataSearchService {
    private final ApiRequestBuilder<List<StandardDataDTO>> apiRequestBuilder;

    public StandardDataSearchServiceImpl(ApiRequestBuilder<List<StandardDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public List<StandardDataDTO> search(StandardDataSearchDTO standardDataSearchDTO) {
        return apiRequestBuilder.setUrl("/std/standardData/search")
                .setObject(standardDataSearchDTO)
                .setResponseType(new ParameterizedTypeReference<List<StandardDataDTO>>() {
                })
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}