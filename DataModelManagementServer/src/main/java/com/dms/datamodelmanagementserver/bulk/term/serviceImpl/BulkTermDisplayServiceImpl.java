package com.dms.datamodelmanagementserver.bulk.term.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.common.utils.BulkMethodUtil;
import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO.BulkTermExcelDataDTOBuilder;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermDisplayService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.global.UrlBuilder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BulkTermDisplayServiceImpl implements BulkTermDisplayService {

    private final ApiRequestBuilder<List<BulkTermExcelDataDTO>> apiRequestBuilder;
    public final UrlBuilder urlBuilder;
    private final int START_ROW = 1;

    
    public BulkTermDisplayServiceImpl(ApiRequestBuilder<List<BulkTermExcelDataDTO>> apiRequestBuilder,
			UrlBuilder urlBuilder) {
		this.apiRequestBuilder = apiRequestBuilder;
		this.urlBuilder = urlBuilder;
	}


	@Override
    public List<BulkTermExcelDataDTO> readTermExcelData(MultipartFile file, String stdAreaName) {
    	
    	ByteArrayResource body = null;
		try {
			body = new ByteArrayResource(file.getBytes()) {
			    @Override
			    public String getFilename() {
			        return file.getOriginalFilename();
			   }
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> http = new HttpEntity<>(headers);

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("file", body);
		bodyMap.add("stdAreaName", stdAreaName);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		http = new HttpEntity<>(bodyMap, headers);
		String serviceUrl = urlBuilder.buildServiceUrl("/std/bulk-term/validate");

		ResponseEntity<List<BulkTermExcelDataDTO>> response = restTemplate.exchange(serviceUrl, HttpMethod.POST, http, new ParameterizedTypeReference<List<BulkTermExcelDataDTO>>() {});
		List<BulkTermExcelDataDTO> dataList = response.getBody();
		return dataList;

//    	HttpHeaders headers = new HttpHeaders();
//    	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//    	
//    	MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
//    	bodyMap.add("file", body);
//    	
//    	HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(bodyMap, headers);
//    	
//            return apiRequestBuilder.setUrl("/std/bulk-term/validate")
//                    .setObject(entity)
//                    .setResponseType(new ParameterizedTypeReference<List<BulkTermExcelDataDTO>>() {})
//                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
//                    .execute();
        } 

 

//    @Override
//    public List<BulkTermExcelDataDTO> readTermExcelData(MultipartFile file, String stdAreaName) {
//        List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList = new ArrayList<>();
//        if (file.isEmpty()) {
//            return bulkTermExcelDataDTOList;
//        }
//        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
//            Sheet sheet = workbook.getSheetAt(1);
//            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
//            bulkTermExcelDataDTOList = rowStream
//                    .filter(row -> row.getRowNum() >= START_ROW)
//                    .map(row -> {
//                        BulkTermExcelDataDTO.BulkTermExcelDataDTOBuilder builder = BulkTermExcelDataDTO.builder();
//
//                        builder.stdAreaId(stdAreaName);
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(0))) {
//                        	if(BulkMethodUtil.isError(row.getCell(0))) {
//                            	builder.isError(true);
//                            } else {
//                            	builder.tempDicLogicalName(BulkMethodUtil.checkStringCell(row.getCell(0)));
//                            }
//                        } 
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(1))) {
//                        	if(BulkMethodUtil.isError(row.getCell(1))) {
//                            	builder.isError(true);
//                            } else {
//                            	builder.dicDescription(BulkMethodUtil.checkStringCell(row.getCell(1)));
//                            }
//                        }
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(2))) {
//                        	if(BulkMethodUtil.isError(row.getCell(2))) {
//                            	builder.isError(true);
//                            } else {
//                            	builder.keyDomainName(BulkMethodUtil.checkStringCell(row.getCell(2)));
//                            }  
//                        } 
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(3))) {
//                        	if(BulkMethodUtil.isError(row.getCell(3))) {
//                            	builder.isError(true);
//                            } else {
//                            	builder.domainTypeCode(BulkMethodUtil.checkStringCell(row.getCell(3)));
//                            }
//                        }
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(4))) {
//                        	if(BulkMethodUtil.isError(row.getCell(4))) {
//                            	builder.isError(true);
//                            } else {
//                            	builder.domainGroupName(BulkMethodUtil.checkStringCell(row.getCell(4)));
//                            }
//                        }
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(5))) {
//                        	if(BulkMethodUtil.isError(row.getCell(5))) {
//                            	builder.isError(true);
//                            } else {
//                            	builder.dataTypeCode(BulkMethodUtil.checkStringCell(row.getCell(5)));
//                            }
//                        } 
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(6))) {
//                        	if(BulkMethodUtil.isError(row.getCell(6))) {
//                            	builder.isError(true);
//                            } else {
//                                builder.dataLength(BulkMethodUtil.checkIntCell(row.getCell(6)));                            	
//                            }
//                        } 
//
//                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(7))) {
//                        	if(BulkMethodUtil.isError(row.getCell(7))) {
//                            	builder.isError(true);
//                            } else {
//                            	builder.dataScale(BulkMethodUtil.checkIntCell(row.getCell(7)));
//                            }
//                        }
//
//                        return builder.build();
//                    })
//                    .filter(row -> !isNullOfRow(row))
//                    .collect(Collectors.toList());
// 
//            return apiRequestBuilder.setUrl("/std/bulk-term/validate")
//                    .setObject(bulkTermExcelDataDTOList)
//                    .setResponseType(new ParameterizedTypeReference<List<BulkTermExcelDataDTO>>() {})
//                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
//                    .execute();
//            
//        } catch (IOException e) {
//            return bulkTermExcelDataDTOList;
//        }
//    }
// 
    private boolean isNullOfRow(BulkTermExcelDataDTO dto) {
    	return dto.getTempDicLogicalName() == null && dto.getDicDescription() == null && dto.getKeyDomainName() == null && dto.getDomainTypeCode() == null && dto.getDomainGroupName() == null && dto.getDataTypeCode() == null && dto.getDataLength() == null && dto.getDataScale() == null;
    }
    

}
