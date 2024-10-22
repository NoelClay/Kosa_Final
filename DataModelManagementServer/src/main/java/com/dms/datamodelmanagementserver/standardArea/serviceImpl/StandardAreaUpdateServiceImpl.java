package com.dms.datamodelmanagementserver.standardArea.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StandardAreaUpdateServiceImpl implements StandardAreaUpdateService {
    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public StandardAreaUpdateServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public boolean update(StandardAreaDTO standardAreaDTO) {
        return apiRequestBuilder.setUrl("/std/standardArea/update")
                .setObject(standardAreaDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
