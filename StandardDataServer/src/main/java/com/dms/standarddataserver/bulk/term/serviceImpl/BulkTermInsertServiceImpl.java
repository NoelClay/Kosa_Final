package com.dms.standarddataserver.bulk.term.serviceImpl;

import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.bulk.term.dto.BulkTermDataDTO;
import com.dms.standarddataserver.bulk.term.dto.WordOfTermDTO;
import com.dms.standarddataserver.bulk.term.mapper.BulkTermMapper;
import com.dms.standarddataserver.bulk.term.service.BulkTermInsertService;
import com.dms.standarddataserver.bulk.word.mapper.BulkWordMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BulkTermInsertServiceImpl implements BulkTermInsertService {

    private final BulkDomainMapper bulkDomainMapper;
    private final BulkTermMapper bulkTermMapper;
    private final BulkWordMapper bulkWordMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public BulkTermInsertServiceImpl(BulkDomainMapper bulkDomainMapper, BulkTermMapper bulkTermMapper, BulkWordMapper bulkWordMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkDomainMapper = bulkDomainMapper;
        this.bulkTermMapper = bulkTermMapper;
        this.bulkWordMapper = bulkWordMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<BulkTermDataDTO> insertBulkTerm(List<BulkTermDataDTO> bulkTermDataDTOList) {
        return bulkTermDataDTOList.stream()
                .map(this::processBulkTermDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private BulkTermDataDTO processBulkTermDto(BulkTermDataDTO dto) {
        updateBulkTermDataDto(dto);
        int insertResult = bulkTermMapper.insertBulkTerm(dto);

        if (insertResult > 0) {
            String termId = dto.getDicId();
            createWordsOfTerm(dto, termId, dto.getStdAreaId());
        }
        return dto;
    }

    private void updateBulkTermDataDto(BulkTermDataDTO dto) {
        String stdAreaId = standardAreaSelectOneService.selectOne(dto.getStdAreaId()).getStdAreaId();
        String domainId;
        if (StringUtils.hasText(dto.getDomainGroupName())) {
            String domainGroupId = bulkDomainMapper.getRegisteredDomainGroupId(dto.getDomainGroupName(), stdAreaId);
            domainId = bulkDomainMapper.findDomainIdWithDomainGroupId(dto.getDomainName(), domainGroupId, stdAreaId);
            
        } else {
            domainId = bulkDomainMapper.findDomainId(dto.getDomainName(), stdAreaId);
        }
        dto.setStdAreaId(stdAreaId);
        dto.setDicId(UUID.randomUUID().toString());
        dto.setDicGbnCd("term");
        dto.setDomainId(domainId);
    }

    private void createWordsOfTerm(BulkTermDataDTO dto, String termId, String stdAreaId) {
        List<String> words = List.of(dto.getTempDicLogicalName().trim().split("_"));
        for (int i = 0; i < words.size(); i++) {
            WordOfTermDTO wordOfTermDTO = new WordOfTermDTO();
            String dicId = bulkTermMapper.findDicIdByWordName(words.get(i), stdAreaId);
            if (StringUtils.hasText(dicId)) {
            	wordOfTermDTO.setStdAreaId(stdAreaId);
                wordOfTermDTO.setTermId(termId);
                wordOfTermDTO.setOrderNum(i + 1);
                wordOfTermDTO.setDicId(dicId);
                bulkTermMapper.insertWordOfTerm(wordOfTermDTO);
            }
        }
    }
}
