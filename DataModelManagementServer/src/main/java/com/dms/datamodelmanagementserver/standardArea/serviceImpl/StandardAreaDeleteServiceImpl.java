package com.dms.datamodelmanagementserver.standardArea.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaDeleteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class StandardAreaDeleteServiceImpl implements StandardAreaDeleteService {
    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public StandardAreaDeleteServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }
    @Override
    public boolean delete(StandardAreaDTO standardAreaDTO) {
        return apiRequestBuilder.setUrl("/std/standardArea/delete")
                .setObject(standardAreaDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
