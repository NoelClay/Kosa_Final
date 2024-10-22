package com.dms.datamodelmanagementserver.bulk.word.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.common.utils.BulkMethodUtil;
import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.service.BulkWordDisplayService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BulkWordDisplayServiceImpl implements BulkWordDisplayService {

    private final ApiRequestBuilder<List<WordExcelDataDTO>> apiRequestBuilder;
    private final int START_ROW = 1;

    public BulkWordDisplayServiceImpl(ApiRequestBuilder<List<WordExcelDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<WordExcelDataDTO> readWordExcelData(MultipartFile file, String stdAreaName) {
        List<WordExcelDataDTO> wordExcelDataDTOs = new ArrayList<>();
        if (file.isEmpty()) {
            return wordExcelDataDTOs;
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(1);

            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
            wordExcelDataDTOs = rowStream
                    .filter(row -> row.getRowNum() >= START_ROW)
                    .map(row -> {
                        WordExcelDataDTO.WordExcelDataDTOBuilder builder = WordExcelDataDTO.builder();

                        builder.stdAreaId(stdAreaName);

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(0))) {
                        	if(BulkMethodUtil.isError(row.getCell(0))) {
                            	builder.isError(true);
                            } else {
                                builder.dicLogicalName(BulkMethodUtil.checkStringCell(row.getCell(0)));                            	
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(1))) {
                        	if(BulkMethodUtil.isError(row.getCell(1))) {
                            	builder.isError(true);
                            } else {
                                builder.dicPhysicalName(BulkMethodUtil.checkStringCell(row.getCell(1)));                           	
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(2))) {
                        	if(BulkMethodUtil.isError(row.getCell(2))) {
                            	builder.isError(true);
                            } else {
                            	builder.dicPhysicalFullName(BulkMethodUtil.checkStringCell(row.getCell(2)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(3))) {
                        	if(BulkMethodUtil.isError(row.getCell(3))) {
                            	builder.isError(true);
                            } else {
                                builder.entitySuffix(BulkMethodUtil.checkStringCell(row.getCell(3)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(4))) {
                        	if(BulkMethodUtil.isError(row.getCell(4))) {
                            	builder.isError(true);
                            } else {
                                builder.attributeSuffix(BulkMethodUtil.checkStringCell(row.getCell(4)));                            	
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(5))) {
                        	if(BulkMethodUtil.isError(row.getCell(5))) {
                            	builder.isError(true);
                            } else {
                                builder.synonym(extractSynonyms(BulkMethodUtil.checkStringCell(row.getCell(5))));                           	
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(6))) {
                        	if(BulkMethodUtil.isError(row.getCell(6))) {
                            	builder.isError(true);
                            } else {
                                builder.dicDescription(BulkMethodUtil.checkStringCell(row.getCell(6)));                            	
                            }
                        }

                        return builder.build();
                    })
                    .filter(row -> !isNullOfRow(row))
                    .collect(Collectors.toList());

            return apiRequestBuilder.setUrl("/std/bulk-word/validate")
                    .setObject(wordExcelDataDTOs)
                    .setResponseType(new ParameterizedTypeReference<List<WordExcelDataDTO>>() {})
                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                    .execute();
            
        } catch (IOException e) {
            return wordExcelDataDTOs;
        }
    }

    private List<String> extractSynonyms(String synonym) {
        return Arrays.asList(synonym.split(",")).stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
    
    private boolean isNullOfRow(WordExcelDataDTO dto) {
    	return dto.getDicLogicalName() == null && dto.getDicPhysicalName() == null && dto.getDicPhysicalFullName() == null && dto.getEntitySuffix() == null && dto.getAttributeSuffix() == null && dto.getSynonym()== null && dto.getDicDescription() == null;
    }
}
