package com.dms.datamodelmanagementserver.collectionBook.serviceImpl;

import com.dms.datamodelmanagementserver.collectionBook.service.CollectionBookDisplayService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataSearchDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionBookDisplayServiceImpl implements CollectionBookDisplayService {

    private final ApiRequestBuilder<List<StandardDataDTO>> apiRequestBuilder;

    public CollectionBookDisplayServiceImpl(ApiRequestBuilder<List<StandardDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<StandardDataDTO> searchCollection(StandardDataSearchDTO standardDataSearchDTO) {
        return apiRequestBuilder.setUrl("/std/collectionBook/display")
                .setObject(standardDataSearchDTO)
                .setResponseType(new ParameterizedTypeReference<List<StandardDataDTO>>() {
                })
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
