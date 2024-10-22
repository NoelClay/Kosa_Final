package com.dms.datamodelmanagementserver.standardData.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.service.StandardDataCheckSynonymService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class StandardDataCheckSynonymServiceImpl implements StandardDataCheckSynonymService {
    private final ApiRequestBuilder<StandardDataDTO> apiRequestBuilder;

    public StandardDataCheckSynonymServiceImpl(ApiRequestBuilder<StandardDataDTO> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public StandardDataDTO checkSynonym(StandardDataDTO standardDataDTO) {
        return apiRequestBuilder.setUrl("/std/standardData/checkSynonym")
                .setObject(standardDataDTO)
                .setResponseType(StandardDataDTO.class)
                .setMethod(HttpMethod.POST)
                //.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
