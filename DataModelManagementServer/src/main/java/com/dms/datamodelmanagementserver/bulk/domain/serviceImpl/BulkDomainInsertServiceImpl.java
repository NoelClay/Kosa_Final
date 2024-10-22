package com.dms.datamodelmanagementserver.bulk.domain.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.domain.service.BulkDomainInsertService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BulkDomainInsertServiceImpl implements BulkDomainInsertService {

    private final ApiRequestBuilder<List<DomainExcelDataDTO>> apiRequestBuilder;

    @Autowired
    public BulkDomainInsertServiceImpl(ApiRequestBuilder<List<DomainExcelDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public List<DomainExcelDataDTO> insertBulkDomain(List<DomainExcelDataDTO> domainExcelDataDTOList) {
        return apiRequestBuilder.setUrl("/std/bulk-domain/insert")
                .setObject(domainExcelDataDTOList)
                .setResponseType(new ParameterizedTypeReference<List<DomainExcelDataDTO>>() {})
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
