package com.dms.datamodelmanagementserver.standardArea.serviceImpl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.mapper.StandardAreaSelectOneMapper;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

@Service
public class StandardAreaSelectOneServiceImpl implements StandardAreaSelectOneService{
	
	private final StandardAreaSelectOneMapper standardAreaSelectOneMapper;
//	private final ApiRequestBuilder<StandardAreaDTO> apiRequestBuilder;
//
//	public StandardAreaSelectOneServiceImpl(ApiRequestBuilder<StandardAreaDTO> apiRequestBuilder) {
//		this.apiRequestBuilder = apiRequestBuilder;
//	}

	public StandardAreaSelectOneServiceImpl(StandardAreaSelectOneMapper standardAreaSelectOneMapper) {
		this.standardAreaSelectOneMapper = standardAreaSelectOneMapper;
	}
	
	@Override
	public StandardAreaDTO selectOne(String standardAreaName) {
		return standardAreaSelectOneMapper.selectOne(standardAreaName);
	}

//	@Override
//	public StandardAreaDTO selectOne(String standardAreaName) {
//
//		return apiRequestBuilder.setUrl("/std/standardArea/selectOne")
//	              .setObject(standardAreaName)
//	              .setResponseType(StandardAreaDTO.class)
//	              .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
//	              .execute();
//	}

}
