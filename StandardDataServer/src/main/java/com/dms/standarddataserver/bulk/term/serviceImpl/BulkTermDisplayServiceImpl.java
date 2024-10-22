package com.dms.standarddataserver.bulk.term.serviceImpl;

import com.dms.standarddataserver.bulk.common.utils.BulkMethodUtil;
import com.dms.standarddataserver.bulk.common.utils.DomainMethodUtil;
import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.enums.DomainValidationMessage;
import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.standarddataserver.bulk.term.enums.TermValidationMessage;
import com.dms.standarddataserver.bulk.term.mapper.BulkTermMapper;
import com.dms.standarddataserver.bulk.term.service.BulkTermDisplayService;
import com.dms.standarddataserver.bulk.word.dto.BulkWordDataDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BulkTermDisplayServiceImpl implements BulkTermDisplayService {

    private final BulkDomainMapper bulkDomainMapper;
    private final BulkTermMapper bulkTermMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final int START_ROW = 1;

    public BulkTermDisplayServiceImpl(BulkDomainMapper bulkDomainMapper, BulkTermMapper bulkTermMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkDomainMapper = bulkDomainMapper;
        this.bulkTermMapper = bulkTermMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    
    @Override
    public List<BulkTermExcelDataDTO> validateBulkTerm(MultipartFile file, String stdAreaName) {
        Set<String> tempDicLogicalNameSet = new HashSet<>();
        String stdAreaId = standardAreaSelectOneService.selectOne(stdAreaName).getStdAreaId();
        
        List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList = new ArrayList<>();
        if (file.isEmpty()) {
            return bulkTermExcelDataDTOList;
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(1);
            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), true);
            bulkTermExcelDataDTOList = rowStream
                    .filter(row -> row.getRowNum() >= START_ROW)
                    .map(row -> {
                        BulkTermExcelDataDTO.BulkTermExcelDataDTOBuilder builder = BulkTermExcelDataDTO.builder();

                        builder.stdAreaId(stdAreaName);

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(0))) {
                        	if(BulkMethodUtil.isError(row.getCell(0))) {
                            	builder.isError(true);
                            } else {
                            	builder.tempDicLogicalName(BulkMethodUtil.checkStringCell(row.getCell(0)));
                            }
                        } 

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(1))) {
                        	if(BulkMethodUtil.isError(row.getCell(1))) {
                            	builder.isError(true);
                            } else {
                            	builder.dicDescription(BulkMethodUtil.checkStringCell(row.getCell(1)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(2))) {
                        	if(BulkMethodUtil.isError(row.getCell(2))) {
                            	builder.isError(true);
                            } else {
                            	builder.keyDomainName(BulkMethodUtil.checkStringCell(row.getCell(2)));
                            }  
                        } 

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(3))) {
                        	if(BulkMethodUtil.isError(row.getCell(3))) {
                            	builder.isError(true);
                            } else {
                            	builder.domainTypeCode(BulkMethodUtil.checkStringCell(row.getCell(3)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(4))) {
                        	if(BulkMethodUtil.isError(row.getCell(4))) {
                            	builder.isError(true);
                            } else {
                            	builder.domainGroupName(BulkMethodUtil.checkStringCell(row.getCell(4)));
                            }
                        }

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(5))) {
                        	if(BulkMethodUtil.isError(row.getCell(5))) {
                            	builder.isError(true);
                            } else {
                            	builder.dataTypeCode(BulkMethodUtil.checkStringCell(row.getCell(5)));
                            }
                        } 

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(6))) {
                        	if(BulkMethodUtil.isError(row.getCell(6))) {
                            	builder.isError(true);
                            } else {
                                builder.dataLength(BulkMethodUtil.checkIntCell(row.getCell(6)));                            	
                            }
                        } 

                        if (BulkMethodUtil.isNotNullOfCell(row.getCell(7))) {
                        	if(BulkMethodUtil.isError(row.getCell(7))) {
                            	builder.isError(true);
                            } else {
                            	builder.dataScale(BulkMethodUtil.checkIntCell(row.getCell(7)));
                            }
                        }

                        return builder.build();
                    })
                    .filter(row -> !isNullOfRow(row))
                    .map(dto -> {
                        if (isNotRequiredField(dto)) {
                            dto.updateReason(TermValidationMessage.REQUIRED_FIELD_MISSING.getMessage());
                            return dto;
                        } else {
                        	
                        	if (dto.isError()) {
                        		dto.updateReason(TermValidationMessage.CELL_ERROR.getMessage());
                        		return dto;
                        	}
                        	
                            if (StringUtils.hasText(dto.getTempDicLogicalName()) && !tempDicLogicalNameSet.add(dto.getTempDicLogicalName())) {
                                dto.updateReason(TermValidationMessage.TERM_NAME_DUPLICATE_IN_EXCEL.getMessage());
                                return dto;
                            }

                            dto.setDomainTypeCode(dto.getDomainTypeCode());// 필요있나?

                            if (DomainMethodUtil.isNotMatchDataType(dto.getDataTypeCode())) {
                                dto.updateReason(DomainValidationMessage.DATA_TYPE_MISMATCH.getMessage());
                                return dto;
                            } 
                            else {
                                dto.setDataTypeCode(dto.getDataTypeCode()); // 필요있나?
                            }

                            if (StringUtils.hasText(dto.getDomainGroupName())) {
                                String domainGroupId = getRegisteredDomainGroupId(dto, stdAreaId);
                                if (StringUtils.hasText(domainGroupId)) {
                                    dto.setDomainGroupId(domainGroupId);
                                } else {
                                    dto.updateReason(DomainValidationMessage.NOT_REGISTERED_IN_DOMAIN_GROUP.getMessage());
                                    return dto;
                                }
                            }

                            if (!Optional.ofNullable(dto.getReason()).isPresent()) {
                                String keyDomainName = dto.getKeyDomainName();
                                String dataTypeCode = dto.getDataTypeCode();
                                int dataLength = dto.getDataLength() == null ? 0 : dto.getDataLength();
                                int dataScale = dto.getDataScale() == null ? 0 : dto.getDataScale();
                                String domainName = DomainMethodUtil.createDomainName(keyDomainName, dataTypeCode, dataLength, dataScale);

                                if (StringUtils.hasText(dto.getDomainGroupId())) {
                                    if (bulkDomainMapper.isDuplicatedByDomainGroupId(domainName, dto.getDomainGroupId(), stdAreaId) == 0) {
                                        dto.updateReason(TermValidationMessage.NOT_REGISTERED_DOMAIN_IN_DOMAIN_GROUP.getMessage());
                                        return dto;
                                    }
                                } else {
                                    if (bulkDomainMapper.isDuplicated(domainName, stdAreaId) == 0) {
                                        dto.updateReason(TermValidationMessage.NOT_REGISTERED_DOMAIN.getMessage());
                                        return dto;
                                    } 
                                }

                                if (!Optional.ofNullable(dto.getReason()).isPresent()) {
                                    dto.updateDomainName(domainName);
                                }
                            }

                            if (!Optional.ofNullable(dto.getReason()).isPresent()) {
                                if (isDuplicatedInLogicalName(dto, stdAreaId)) {
                                    dto.updateReason(TermValidationMessage.TERM_NAME_DUPLICATE_IN_DB.getMessage());
                                    return dto;
                                } else {
                                    this.checkWordsOfTerm(dto, stdAreaId);
                                    if (!Optional.ofNullable(dto.getReason()).isPresent()) {
                                        createTermLogicalName(dto);
                                        createTermPhysicalName(dto, stdAreaId);
                                        dto.updateReason(TermValidationMessage.VALIDATION_PASS.getMessage());
                                    }
                                }
                            }
                        }
                        return dto;
                    })
                    .collect(Collectors.toList());
 
            return bulkTermExcelDataDTOList;
            
        } catch (IOException e) {
            return bulkTermExcelDataDTOList;
        }
                                                                         
    }
//    @Override
//    public List<BulkTermExcelDataDTO> validateBulkTerm(List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList) {
//        Set<String> tempDicLogicalNameSet = new HashSet<>();
//        String stdAreaId = standardAreaSelectOneService.selectOne(bulkTermExcelDataDTOList.get(0).getStdAreaId()).getStdAreaId();
//        return bulkTermExcelDataDTOList.stream()
//                .map(dto -> {
//                    if (isNotRequiredField(dto)) {
//                        dto.updateReason(TermValidationMessage.REQUIRED_FIELD_MISSING.getMessage());
//                        return dto;
//                    } else {
//                    	
//                    	if (dto.isError()) {
//                    		dto.updateReason(TermValidationMessage.CELL_ERROR.getMessage());
//                    		return dto;
//                    	}
//                    	
//                        if (StringUtils.hasText(dto.getTempDicLogicalName()) && !tempDicLogicalNameSet.add(dto.getTempDicLogicalName())) {
//                            dto.updateReason(TermValidationMessage.TERM_NAME_DUPLICATE_IN_EXCEL.getMessage());
//                            return dto;
//                        }
//
//                        dto.setDomainTypeCode(dto.getDomainTypeCode());// 필요있나?
//
//                        if (DomainMethodUtil.isNotMatchDataType(dto.getDataTypeCode())) {
//                            dto.updateReason(DomainValidationMessage.DATA_TYPE_MISMATCH.getMessage());
//                            return dto;
//                        } else {
//                            dto.setDataTypeCode(dto.getDataTypeCode()); // 필요있나?
//                        }
//
//                        if (StringUtils.hasText(dto.getDomainGroupName())) {
//                            String domainGroupId = getRegisteredDomainGroupId(dto, stdAreaId);
//                            if (StringUtils.hasText(domainGroupId)) {
//                                dto.setDomainGroupId(domainGroupId);
//                            } else {
//                                dto.updateReason(DomainValidationMessage.NOT_REGISTERED_IN_DOMAIN_GROUP.getMessage());
//                                return dto;
//                            }
//                        }
//
//                        if (!Optional.ofNullable(dto.getReason()).isPresent()) {
//                            String keyDomainName = dto.getKeyDomainName();
//                            String dataTypeCode = dto.getDataTypeCode();
//                            int dataLength = dto.getDataLength() == null ? 0 : dto.getDataLength();
//                            int dataScale = dto.getDataScale() == null ? 0 : dto.getDataScale();
//                            String domainName = DomainMethodUtil.createDomainName(keyDomainName, dataTypeCode, dataLength, dataScale);
//
//                            if (StringUtils.hasText(dto.getDomainGroupId())) {
//                                if (bulkDomainMapper.isDuplicatedByDomainGroupId(domainName, dto.getDomainGroupId(), stdAreaId) == 0) {
//                                    dto.updateReason(TermValidationMessage.NOT_REGISTERED_DOMAIN_IN_DOMAIN_GROUP.getMessage());
//                                    return dto;
//                                }
//                            } else {
//                                if (bulkDomainMapper.isDuplicated(domainName, stdAreaId) == 0) {
//                                    dto.updateReason(TermValidationMessage.NOT_REGISTERED_DOMAIN.getMessage());
//                                    return dto;
//                                }
//                            }
//
//                            if (!Optional.ofNullable(dto.getReason()).isPresent()) {
//                                dto.updateDomainName(domainName);
//                            }
//                        }
//
//                        if (!Optional.ofNullable(dto.getReason()).isPresent()) {
//                            if (isDuplicatedInLogicalName(dto, stdAreaId)) {
//                                dto.updateReason(TermValidationMessage.TERM_NAME_DUPLICATE_IN_DB.getMessage());
//                                return dto;
//                            } else {
//                                this.checkWordsOfTerm(dto, stdAreaId);
//                                if (!Optional.ofNullable(dto.getReason()).isPresent()) {
//                                    createTermLogicalName(dto);
//                                    createTermPhysicalName(dto, stdAreaId);
//                                    dto.updateReason(TermValidationMessage.VALIDATION_PASS.getMessage());
//                                }
//                            }
//                        }
//                    }
//                    System.out.println(dto);
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

    private boolean isNullOfRow(BulkTermExcelDataDTO dto) {
    	return dto.getTempDicLogicalName() == null && dto.getDicDescription() == null && dto.getKeyDomainName() == null && dto.getDomainTypeCode() == null && dto.getDomainGroupName() == null && dto.getDataTypeCode() == null && dto.getDataLength() == null && dto.getDataScale() == null;
    }
    
    private boolean isNotRequiredField(BulkTermExcelDataDTO dto) {
        return !StringUtils.hasText(dto.getTempDicLogicalName()) || !StringUtils.hasText(dto.getKeyDomainName()) || !StringUtils.hasText(dto.getDomainTypeCode()) || !StringUtils.hasText(dto.getDataTypeCode());
    }

    private boolean isDuplicatedInBulkTermExcel(List<BulkTermExcelDataDTO> bulkTermExcelDataDTOs, String tempDicLogicalName) {
        if (bulkTermExcelDataDTOs.isEmpty()) {
            return false;
        }
        return bulkTermExcelDataDTOs.stream().anyMatch(prevDto -> prevDto.getTempDicLogicalName().equals(tempDicLogicalName));
    }

    private String getRegisteredDomainGroupId(BulkTermExcelDataDTO dto, String stdAreaId) {
        return bulkDomainMapper.getRegisteredDomainGroupId(dto.getDomainGroupName(), stdAreaId);
    }

    private boolean isDuplicatedInLogicalName(BulkTermExcelDataDTO dto, String stdAreaId) {
        String dicLogicalName = replaceUnderscoresWithSpaces(dto);
        return bulkTermMapper.isDuplicatedInLogicalName(dicLogicalName, stdAreaId) != 0;
    }

    private void checkWordsOfTerm(BulkTermExcelDataDTO dto, String stdAreaID) {
        List<String> words = splitTermByUnderscores(dto);

        String lastWordOfTerm = words.get(words.size() - 1);
        BulkWordDataDTO bulkWordDataDTOOfLastWord = checkLastWordOfTerm(lastWordOfTerm,stdAreaID);
        if (bulkWordDataDTOOfLastWord == null) {
            dto.updateReason("[" + lastWordOfTerm + "]" + TermValidationMessage.NOT_REGISTERED_WORD.getMessage());
        } else {
            if ("Y".equals(bulkWordDataDTOOfLastWord.getCheckedStandard())) {
                if ("N".equals(bulkWordDataDTOOfLastWord.getAttributeSuffix())) {
                    dto.updateReason("[" + lastWordOfTerm + "]" + TermValidationMessage.NOT_ATTRIBUTE_SUFFIX.getMessage());
                }
            } else {
                dto.updateReason("[" + lastWordOfTerm + "]" + TermValidationMessage.NOT_STANDARD_WORD.getMessage());
            }
        }

        List<String> wordsWithoutLast = new ArrayList<>(words.subList(0, words.size() - 1));
        wordsWithoutLast.stream()
                .forEach(word -> {
                    String checkedStandard = checkRegisteredWord(word, stdAreaID);
                    if (!StringUtils.hasText(checkedStandard)) {
                        dto.updateReason("[" + word + "]" + TermValidationMessage.NOT_REGISTERED_WORD.getMessage());
                    } else {
                        if ("N".equals(checkedStandard)) {
                            dto.updateReason("[" + word + "]" + TermValidationMessage.NOT_STANDARD_WORD.getMessage());
                        }
                    }
                });
    }

    private List<String> splitTermByUnderscores(BulkTermExcelDataDTO dto) {
        return List.of(dto.getTempDicLogicalName().trim().split("_"));
    }

    private String checkRegisteredWord(String word, String stdAreaId) {
        return bulkTermMapper.checkRegisteredWord(word, stdAreaId);
    }

    private BulkWordDataDTO checkLastWordOfTerm(String word, String stdAreaId) {
        return bulkTermMapper.checkLastWordOfTerm(word, stdAreaId);
    }

    private String replaceUnderscoresWithSpaces(BulkTermExcelDataDTO dto) {
        return dto.getTempDicLogicalName().trim().replace("_", " ");
    }

    private void createTermLogicalName(BulkTermExcelDataDTO dto) {
        String dicLogicalName = replaceUnderscoresWithSpaces(dto);
        dto.setDicLogicalName(dicLogicalName);
    }

    private void createTermPhysicalName(BulkTermExcelDataDTO dto, String stdAreaId) {
        List<String> words = splitTermByUnderscores(dto);
        String dicPhysicalName = words.stream()
                .map(word -> {
                    String physicalNameOfWord = bulkTermMapper.findPhysicalNameOfWord(word, stdAreaId);
                    return physicalNameOfWord;
                })
                .collect(Collectors.joining("_"));
        dto.setDicPhysicalName(dicPhysicalName);
    }

}
