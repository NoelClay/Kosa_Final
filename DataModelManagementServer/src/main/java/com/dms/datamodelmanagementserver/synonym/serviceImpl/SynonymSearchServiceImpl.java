package com.dms.datamodelmanagementserver.synonym.serviceImpl;

import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;
import com.dms.datamodelmanagementserver.synonym.dto.SynonymDTO;
import com.dms.datamodelmanagementserver.synonym.mapper.SynonymSearchMapper;
import com.dms.datamodelmanagementserver.synonym.service.SynonymSearchService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynonymSearchServiceImpl implements SynonymSearchService {

	private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final SynonymSearchMapper synonymSearchMapper;

	public SynonymSearchServiceImpl(StandardAreaSelectOneService standardAreaSelectOneService,
			SynonymSearchMapper synonymSearchMapper) {
		super();
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.synonymSearchMapper = synonymSearchMapper;
	}

	@Override
    public List<SynonymDTO> selectList(SynonymDTO synonymDTO) {
        String stdAreaId = standardAreaSelectOneService.selectOne(synonymDTO.getStdAreaId()).getStdAreaId();
        synonymDTO.setStdAreaId(stdAreaId);
        return synonymSearchMapper.selectList(synonymDTO);
    }
}
