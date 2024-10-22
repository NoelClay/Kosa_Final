package com.dms.standarddataserver.bulk.domain.serviceImpl;

import com.dms.standarddataserver.bulk.common.utils.DomainMethodUtil;
import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.enums.DomainValidationMessage;
import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.bulk.domain.service.BulkDomainDisplayService;
import com.dms.standarddataserver.bulk.term.enums.TermValidationMessage;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BulkDomainDisplayServiceImpl implements BulkDomainDisplayService {

    private final BulkDomainMapper bulkDomainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public BulkDomainDisplayServiceImpl(BulkDomainMapper bulkDomainMapper,
                                        StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkDomainMapper = bulkDomainMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<DomainExcelDataDTO> validateBulkDomain(List<DomainExcelDataDTO> domainExcelDataDTOList) {
        Set<String> domainNameSet = new HashSet<>();
        Map<String, Set<String>> domainNameMap = new HashMap<>();
        String stdAreaName = domainExcelDataDTOList.get(0).getStdAreaId();
        String stdAreaId = standardAreaSelectOneService.selectOne(stdAreaName).getStdAreaId();
        
        return domainExcelDataDTOList.stream()
                .map(dto -> {
                    if (isNotRequiredField(dto)) {
                        dto.updateReason(DomainValidationMessage.REQUIRED_FIELD_MISSING.getMessage());
                        return dto;
                    } else {
                    	
                    	if (dto.isError()) {
                    		dto.updateReason(DomainValidationMessage.CELL_ERROR.getMessage());
                    	}
                    	
//                        if (DomainMethodUtil.isNotMatchDomainTypeCode(dto.getDomainTypeCode())) {
//                            dto.updateReason(DomainValidationMessage.DOMAIN_TYPE_MISMATCH.getMessage());
//                        } else {
//                            dto.setDomainTypeCode(DomainMethodUtil.getDomainType(dto.getDomainTypeCode()));
//                        }
                    	dto.setDomainTypeCode(dto.getDomainTypeCode());

                        if (DomainMethodUtil.isNotMatchDataType(dto.getDataTypeCode())) {
                            dto.updateReason(DomainValidationMessage.DATA_TYPE_MISMATCH.getMessage());
                        } else {
                            dto.setDataTypeCode(dto.getDataTypeCode());
                        }

                        if (StringUtils.hasText(dto.getDomainGroupName())) {
                            String domainGroupId = getRegisteredDomainGroupId(dto, stdAreaId);
                            if (StringUtils.hasText(domainGroupId)) {
                                dto.setDomainGroupId(domainGroupId);
                            } else {
                                dto.updateReason(DomainValidationMessage.NOT_REGISTERED_IN_DOMAIN_GROUP.getMessage());
                            }
                        }

                        if (!Optional.ofNullable(dto.getReason()).isPresent()) {
                            String keyDomainName = dto.getKeyDomainName();
                            String dataTypeCode = dto.getDataTypeCode();
                            int dataLength = dto.getDataLength() == null ? 0 : dto.getDataLength();
                            int dataScale = dto.getDataScale() == null ? 0 : dto.getDataScale();
                            String domainName = DomainMethodUtil.createDomainName(keyDomainName, dataTypeCode, dataLength, dataScale);
                            
                            if (!StringUtils.hasText(dto.getDomainGroupName()) && !domainNameSet.add(domainName)) {
                            	dto.updateReason(DomainValidationMessage.DOMAIN_NAME_DUPLICATE_IN_EXCEL_NOT_DOMAIN_GROUP.getMessage());
                            }
                            
                            if (StringUtils.hasText(dto.getDomainGroupName())) {
                            	if (!domainNameMap.containsKey(dto.getDomainGroupName())) {
                            		domainNameMap.put(dto.getDomainGroupName(), new HashSet<>());
                            		domainNameMap.get(dto.getDomainGroupName()).add(domainName);
                            	} else {
                            		if (!domainNameMap.get(dto.getDomainGroupName()).add(domainName)) {
                            			dto.updateReason(DomainValidationMessage.DOMAIN_NAME_DUPLICATE_IN_EXCEL_EXIST_DOMAIN_GROUP.getMessage());
                            		}
                            	}
                            }

                            if (StringUtils.hasText(dto.getDomainGroupId())) {
                                if (bulkDomainMapper.isDuplicatedByDomainGroupId(domainName, dto.getDomainGroupId(), stdAreaId) > 0) {
                                    dto.updateReason(DomainValidationMessage.DOMAIN_NAME_DUPLICATE_IN_DOMAIN_GROUP.getMessage());
                                }
                            } else {
                                if (bulkDomainMapper.isDuplicated(domainName, stdAreaId) > 0) {
                                    dto.updateReason(DomainValidationMessage.DOMAIN_NAME_DUPLICATE.getMessage());
                                }
                            }

                            if (!Optional.ofNullable(dto.getReason()).isPresent()) {
                                dto.updateDomainName(domainName);
                                dto.updateReason(DomainValidationMessage.VALIDATION_PASS.getMessage());
                            }
                        }
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    private boolean isNotRequiredField(DomainExcelDataDTO dto) {
        return !StringUtils.hasText(dto.getKeyDomainName()) || !StringUtils.hasText(dto.getDomainTypeCode()) || !StringUtils.hasText(dto.getDataTypeCode());
    }

    private boolean isDuplicatedInExcel(List<DomainExcelDataDTO> domainExcelDataDTOs, String domainName, DomainExcelDataDTO dto) {
        if (domainExcelDataDTOs == null || domainExcelDataDTOs.size() == 0) {
            return false;
        }

        List<String> domainGroupIdList = domainExcelDataDTOs.stream().map(it -> it.getDomainGroupId()).filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (StringUtils.hasText(dto.getDomainGroupId())) {
            if (domainGroupIdList.stream().allMatch(String::isEmpty)) {
                return false;
            }
            return domainGroupIdList.stream().anyMatch(id -> id.equals(dto.getDomainGroupId())) && domainExcelDataDTOs.stream().anyMatch(it -> it.getDomainName().equals(domainName));
        }
        return domainExcelDataDTOs.stream().anyMatch(it -> it.getDomainName().equals(domainName));
    }

    private String getRegisteredDomainGroupId(DomainExcelDataDTO dto, String stdAreaId) {
        return bulkDomainMapper.getRegisteredDomainGroupId(dto.getDomainGroupName(), stdAreaId);
    }
}