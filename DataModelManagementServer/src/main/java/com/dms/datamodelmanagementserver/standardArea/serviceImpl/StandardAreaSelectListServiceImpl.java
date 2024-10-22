package com.dms.datamodelmanagementserver.standardArea.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.global.CookieUtil;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StandardAreaSelectListServiceImpl implements StandardAreaSelectListService {
    private final ApiRequestBuilder<List<StandardAreaDTO>> apiRequestBuilder;

    public StandardAreaSelectListServiceImpl(ApiRequestBuilder<List<StandardAreaDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<StandardAreaDTO> selectList(HttpServletRequest request) {
    	String userId = CookieUtil.getCookie(request);
    	
    	if(!StringUtils.hasText(userId)) {
    		return null;
    	}
        return apiRequestBuilder.setUrl("/std/standardArea/selectList")
        		.setObject(userId)
                .setResponseType(new ParameterizedTypeReference<List<StandardAreaDTO>>() { })
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
    
    @Override
    public List<StandardAreaDTO> selectList(String userId) {
        return apiRequestBuilder.setUrl("/std/standardArea/selectList")
        		.setObject(userId)
                .setResponseType(new ParameterizedTypeReference<List<StandardAreaDTO>>() { })
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
    
}
