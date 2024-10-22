package com.dms.standarddataserver.collectionBook.serviceImpl;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.collectionBook.service.CollectionBookDisplayService;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataSearchDTO;
import com.dms.standarddataserver.standardData.dto.StandardDictionaryDTO;
import com.dms.standarddataserver.standardData.mapper.StandardDataSearchMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionBookDisplayServiceImpl implements CollectionBookDisplayService {
    private final StandardDataSearchMapper standardDataSearchMapper;
    private final BulkDomainMapper bulkDomainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public CollectionBookDisplayServiceImpl(StandardDataSearchMapper standardDataSearchMapper, BulkDomainMapper bulkDomainMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.standardDataSearchMapper = standardDataSearchMapper;
        this.bulkDomainMapper = bulkDomainMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<StandardDataDTO> searchCollection(StandardDataSearchDTO standardDataSearchDTO) {
        StandardAreaDTO standardAreaDTO = standardAreaSelectOneService.selectOne(standardDataSearchDTO.getStandardAreaName());
        String standardAreaId = standardAreaDTO.getStdAreaId();
        List<StandardDictionaryDTO> standardDictionaryDTOList = standardDataSearchMapper.searchDictionaryByStandardArea(standardAreaId, standardDataSearchDTO);
        List<StandardDataDTO> standardDataDTOList = new ArrayList<>();

        if (standardDataSearchDTO.isWord()) {
            standardDictionaryDTOList.stream()
                    .filter(dto -> dto.getDicGbnCd().equals("word"))
                    .forEach(dto -> {
                        standardDataDTOList.add(createStandardDataWordDTO(dto, standardAreaDTO));
                    });
        }

        if (standardDataSearchDTO.isTerm()) {
            standardDictionaryDTOList.stream()
                    .filter(dto -> dto.getDicGbnCd().equals("term"))
                    .forEach(dto -> {
                        DomainExcelDataDTO domainExcelDataDTO = bulkDomainMapper.findDomainByDomainId(dto.getDomId(), standardAreaDTO.getStdAreaId());
                        standardDataDTOList.add(createStandardDataTermDTO(dto, standardAreaDTO, domainExcelDataDTO));
                    });
        }

        if (standardDataSearchDTO.isDomain()) {
            List<DomainExcelDataDTO> domainExcelDataDTOList = bulkDomainMapper.selectAllDomain(standardAreaId);
            domainExcelDataDTOList.stream()
                    .forEach(dto -> {
                        standardDataDTOList.add(createStandardDataDomainDTO(dto, standardAreaDTO));
                    });
        }
        return standardDataDTOList;
    }

    private StandardDataDTO createStandardDataWordDTO(StandardDictionaryDTO dictionaryDTO, StandardAreaDTO areaDTO) {
        return StandardDataDTO.builder()
                .dicId(dictionaryDTO.getDicId())
                .standardClassification(areaDTO.getStdAreaNm())
                .division(dictionaryDTO.getDicGbnCd())
                .logicalName(dictionaryDTO.getDicLogNm())
                .physicalName(dictionaryDTO.getDicPhyNm())
                .physicalFullName(dictionaryDTO.getDicPhyFllNm())
                .isStandard(dictionaryDTO.getStandardYn())
                .attributeClassifier(dictionaryDTO.getAttrClssYn())
                .entityClassifier(dictionaryDTO.getEntClssYn())
                .description(dictionaryDTO.getDicDesc())
                .build();
    }

    private StandardDataDTO createStandardDataTermDTO(StandardDictionaryDTO dictionaryDTO, StandardAreaDTO areaDTO, DomainExcelDataDTO domainExcelDataDTO) {
        return StandardDataDTO.builder()
                .dicId(dictionaryDTO.getDicId())
                .standardClassification(areaDTO.getStdAreaNm())
                .division(dictionaryDTO.getDicGbnCd())
                .logicalName(dictionaryDTO.getDicLogNm())
                .physicalName(dictionaryDTO.getDicPhyNm())
//                .isStandard(dictionaryDTO.getStandardYn())
                .description(dictionaryDTO.getDicDesc())
                .domainType(domainExcelDataDTO.getDomainTypeCode())
                .domainName(domainExcelDataDTO.getDomainName())
                .logicalDataType(domainExcelDataDTO.getDataTypeCode())
                .length(String.valueOf(domainExcelDataDTO.getDataLength()))
                .description(dictionaryDTO.getDicDesc())
                .build();
    }

    private StandardDataDTO createStandardDataDomainDTO(DomainExcelDataDTO domainExcelDataDTO, StandardAreaDTO areaDTO) {
        String domainGroupId = domainExcelDataDTO.getDomainGroupId();
        String domainGroupName = "";
        if (StringUtils.hasText(domainGroupId)) {
            domainGroupName = bulkDomainMapper.findDomainGroupName(domainExcelDataDTO.getDomainGroupId(), areaDTO.getStdAreaId());
        }

        return StandardDataDTO.builder()
                .dicId(domainExcelDataDTO.getDomainId())
                .standardClassification(areaDTO.getStdAreaNm())
                .division("domain")
                .logicalName(domainExcelDataDTO.getDomainName())
                .keyDomain(domainExcelDataDTO.getKeyDomainName())
                .domainType(domainExcelDataDTO.getDomainTypeCode())
                .domainName(domainExcelDataDTO.getDomainName())
                .domainGroup(domainGroupName)
                .logicalDataType(domainExcelDataDTO.getDataTypeCode())
                .length(String.valueOf(domainExcelDataDTO.getDataLength()))
                .scale(String.valueOf(domainExcelDataDTO.getDataScale()))
//                .isStandard("Y")
                .description(domainExcelDataDTO.getDomainDescription())
                .dataMin(String.valueOf(domainExcelDataDTO.getDataMin()))
                .dataMax(String.valueOf(domainExcelDataDTO.getDataMax()))
                .build();
    }
}


