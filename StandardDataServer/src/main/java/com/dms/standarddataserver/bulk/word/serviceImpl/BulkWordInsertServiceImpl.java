package com.dms.standarddataserver.bulk.word.serviceImpl;

import com.dms.standarddataserver.bulk.word.dto.BulkWordDataDTO;
import com.dms.standarddataserver.bulk.word.mapper.BulkWordMapper;
import com.dms.standarddataserver.bulk.word.service.BulkWordInsertService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BulkWordInsertServiceImpl implements BulkWordInsertService {

    private final BulkWordMapper bulkWordMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public BulkWordInsertServiceImpl(BulkWordMapper bulkWordMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkWordMapper = bulkWordMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<BulkWordDataDTO> insertBulkWord(List<BulkWordDataDTO> bulkWordDataDTOList) {
        return bulkWordDataDTOList.stream()
                .map(this::processBulkWordDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private BulkWordDataDTO processBulkWordDto(BulkWordDataDTO dto) {
        updateBulkWordDataDto(dto);
        int insertResult = bulkWordMapper.insertBulkWord(dto);

        if (dto.getSynonym() == null) {
            return insertResult > 0 ? dto : null;
        }

        if (insertResult > 0) {
            createSynonym(dto).forEach(synonymDto -> bulkWordMapper.insertBulkWord(synonymDto));
        }
        return dto;
    }

    private List<String> splitSynonym(BulkWordDataDTO dto) {
        return Optional.ofNullable(dto.getSynonym().get(0))
                .filter(synonym -> StringUtils.hasText(synonym))
                .map(synonym -> Arrays.stream(synonym.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private void updateBulkWordDataDto(BulkWordDataDTO dto) {
        String stdAreaId = standardAreaSelectOneService.selectOne(dto.getStdAreaId()).getStdAreaId();
        dto.setStdAreaId(stdAreaId);
        dto.setDicId(UUID.randomUUID().toString());
        dto.setDicGbnCd("word");
        dto.setCheckedStandard("Y");
        dto.setSynonym(splitSynonym(dto));
        if (!StringUtils.hasText(dto.getEntitySuffix())) { dto.setEntitySuffix("N"); }
        if (!StringUtils.hasText(dto.getAttributeSuffix())) { dto.setAttributeSuffix("N"); }
    }

    private List<BulkWordDataDTO> createSynonym(BulkWordDataDTO dto) {
        return dto.getSynonym().stream().map(synonym -> {
            return BulkWordDataDTO.builder()
                   .dicId(UUID.randomUUID().toString())
                   .stdAreaId(dto.getStdAreaId())
                   .alternativeDicId(dto.getDicId())
                   .dicGbnCd("word")
                   .dicLogicalName(synonym)
                   .dicPhysicalName(dto.getDicPhysicalName())
                   .dicPhysicalFullName(dto.getDicPhysicalFullName())
                   .attributeSuffix(dto.getAttributeSuffix())
                   .entitySuffix(dto.getEntitySuffix())
                   .checkedStandard("N")
                   .dicDescription(dto.getDicDescription())
                   .reason(dto.getReason())
                   .build();
        }).collect(Collectors.toList());
    }
}
