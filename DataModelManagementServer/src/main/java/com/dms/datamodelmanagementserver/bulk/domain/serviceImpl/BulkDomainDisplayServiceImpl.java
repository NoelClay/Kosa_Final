package com.dms.datamodelmanagementserver.bulk.domain.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.common.utils.BulkMethodUtil;
import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.domain.service.BulkDomainDisplayService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class BulkDomainDisplayServiceImpl implements BulkDomainDisplayService {

    private final ApiRequestBuilder<List<DomainExcelDataDTO>> apiRequestBuilder;
    private final int START_ROW = 1;

    @Autowired
    public BulkDomainDisplayServiceImpl(ApiRequestBuilder<List<DomainExcelDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<DomainExcelDataDTO> readDomainExcelData(MultipartFile file, String stdAreaName) {
        List<DomainExcelDataDTO> domainExcelDataList = new ArrayList<>();
        List<DomainExcelDataDTO> domainExcelDataDTOList;
        if (file.isEmpty()) {
            return domainExcelDataList;
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(1);

            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
            domainExcelDataList = rowStream
                    .filter(row -> row.getRowNum() >= START_ROW)
                    .map(row -> {
                        DomainExcelDataDTO.DomainExcelDataDTOBuilder builder = DomainExcelDataDTO.builder();

                        builder.stdAreaId(stdAreaName);

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(0))) {
                        	if(BulkMethodUtil.isError(row.getCell(0))) {
                            	builder.isError(true);
                            } else {
                            	builder.keyDomainName(BulkMethodUtil.checkStringCell(row.getCell(0)));
                            }
                        } 

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(1))) {
                        	if(BulkMethodUtil.isError(row.getCell(1))) {
                            	builder.isError(true);
                            } else {
                            	builder.domainTypeCode(BulkMethodUtil.checkStringCell(row.getCell(1)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(2))) {
                        	if(BulkMethodUtil.isError(row.getCell(2))) {
                            	builder.isError(true);
                            } else {
                            	builder.domainGroupName(BulkMethodUtil.checkStringCell(row.getCell(2)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(3))) {
                        	if(BulkMethodUtil.isError(row.getCell(3))) {
                            	builder.isError(true);
                            } else {
                            	builder.dataTypeCode(BulkMethodUtil.checkStringCell(row.getCell(3)));
                            }
                        } 

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(4))) {
                        	if(BulkMethodUtil.isError(row.getCell(4))) {
                            	builder.isError(true);
                            } else {
                            	builder.dataLength(BulkMethodUtil.checkIntCell(row.getCell(4)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(5))) {
                        	if(BulkMethodUtil.isError(row.getCell(5))) {
                            	builder.isError(true);
                            } else {
                            	builder.dataScale(BulkMethodUtil.checkIntCell(row.getCell(5)));
                            }
                        } 

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(6))) {
                        	if(BulkMethodUtil.isError(row.getCell(6))) {
                            	builder.isError(true);
                            } else {
                            	builder.dataMin(BulkMethodUtil.checkIntCell(row.getCell(6)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(7))) {
                        	if(BulkMethodUtil.isError(row.getCell(7))) {
                            	builder.isError(true);
                            } else {
                            	builder.dataMax(BulkMethodUtil.checkIntCell(row.getCell(7)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(8))) {
                        	if(BulkMethodUtil.isError(row.getCell(8))) {
                            	builder.isError(true);
                            } else {
                            	builder.domainDescription(BulkMethodUtil.checkStringCell(row.getCell(8)));
                            }
                        }

                        return builder.build();
                    })
                    .filter(row -> !isNullOfRow(row))
                    .collect(Collectors.toList());

            
            domainExcelDataDTOList = apiRequestBuilder.setUrl("/std/bulk-domain/validate")
                    .setObject(domainExcelDataList)
                    .setResponseType(new ParameterizedTypeReference<List<DomainExcelDataDTO>>() {})
                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                    .execute();
            return domainExcelDataDTOList;

        } catch (IOException e) {
            return domainExcelDataList;
        }
    }
    
    private boolean isNullOfRow(DomainExcelDataDTO dto) {
    	return dto.getKeyDomainName() == null && dto.getDomainDescription() == null && dto.getDomainTypeCode() == null && dto.getDomainGroupName() == null && dto.getDataTypeCode() == null && dto.getDataLength() == null && dto.getDataScale() == null && dto.getDataMin() == null && dto.getDataMax() == null;
    }
}