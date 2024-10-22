package com.dms.datamodelmanagementserver.standardArea.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaCheckDuplicateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StandardAreaCheckDuplicateImpl implements StandardAreaCheckDuplicateService {
    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public StandardAreaCheckDuplicateImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public boolean checkIfDuplicate(String standardAreaName) {
        return apiRequestBuilder.setUrl("/std/standardArea/checkDuplicate")
                .setObject(standardAreaName)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
