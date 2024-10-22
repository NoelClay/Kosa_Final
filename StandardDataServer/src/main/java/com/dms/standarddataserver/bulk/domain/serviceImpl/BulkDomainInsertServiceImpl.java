package com.dms.standarddataserver.bulk.domain.serviceImpl;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.bulk.domain.service.BulkDomainInsertService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BulkDomainInsertServiceImpl implements BulkDomainInsertService {

    private final BulkDomainMapper bulkDomainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public BulkDomainInsertServiceImpl(BulkDomainMapper bulkDomainMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkDomainMapper = bulkDomainMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<DomainExcelDataDTO> insertBulkDomain(List<DomainExcelDataDTO> domainExcelDataDTOList) {
        return domainExcelDataDTOList.stream()
                .map(this::processDomainDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private DomainExcelDataDTO processDomainDTO(DomainExcelDataDTO dto) {
        String stdAreaId = standardAreaSelectOneService.selectOne(dto.getStdAreaId()).getStdAreaId();
        dto.setStdAreaId(stdAreaId);
        dto.setDomainId(UUID.randomUUID().toString());
        int insertResult = bulkDomainMapper.insertBulkDomain(dto);
        return insertResult > 0 ? dto : null;
    }

}
