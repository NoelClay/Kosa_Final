package com.dms.datamodelmanagementserver.standardArea.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.global.CookieUtil;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaInsertService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StandardAreaInsertImpl implements StandardAreaInsertService {
    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public StandardAreaInsertImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public boolean insert(StandardAreaDTO standardAreaDTO, HttpServletRequest request) {
    	
    	standardAreaDTO.setMemberKey(CookieUtil.getCookie(request));
        //std에 직접적인 요청하기.
        return apiRequestBuilder.setUrl("/std/standardArea/insert")
                .setObject(standardAreaDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
