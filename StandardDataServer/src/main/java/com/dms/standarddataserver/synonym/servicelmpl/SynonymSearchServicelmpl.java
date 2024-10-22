package com.dms.standarddataserver.synonym.servicelmpl;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import com.dms.standarddataserver.synonym.dto.SynonymDTO;
import com.dms.standarddataserver.synonym.mapper.SynonymSearchMapper;
import com.dms.standarddataserver.synonym.service.SynonymSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SynonymSearchServicelmpl implements SynonymSearchService {
	private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final SynonymSearchMapper synonymSearchMapper;
    private final LogDefault logDefault;

    public SynonymSearchServicelmpl(StandardAreaSelectOneService standardAreaSelectOneService,
			SynonymSearchMapper synonymSearchMapper, LogDefault logDefault) {
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.synonymSearchMapper = synonymSearchMapper;
		this.logDefault = logDefault;
	}

	@Override
    public List<SynonymDTO> selectList(SynonymDTO synonymDTO) {

        logDefault.logCurrentMethod();

        String stdAreaId = standardAreaSelectOneService.selectOne(synonymDTO.getStdAreaId()).getStdAreaId();
        synonymDTO.setStdAreaId(stdAreaId);

        return synonymSearchMapper.selectList(synonymDTO);
    }

}


