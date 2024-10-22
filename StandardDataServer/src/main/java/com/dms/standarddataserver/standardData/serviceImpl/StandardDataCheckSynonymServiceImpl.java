package com.dms.standarddataserver.standardData.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDictionaryDTO;
import com.dms.standarddataserver.standardData.mapper.StandardDataSearchMapper;
import com.dms.standarddataserver.standardData.service.StandardDataCheckSynonymService;
import com.dms.standarddataserver.standardData.service.StandardDataSearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardDataCheckSynonymServiceImpl implements StandardDataCheckSynonymService {
    private final StandardDataSearchMapper standardDataSearchMapper;
    private final StandardDataSearchService standardDataSearchService;

    public StandardDataCheckSynonymServiceImpl(StandardDataSearchMapper standardDataSearchMapper, StandardDataSearchService standardDataSearchService) {
        this.standardDataSearchMapper = standardDataSearchMapper;
        this.standardDataSearchService = standardDataSearchService;
    }

    @Override
    public StandardDataDTO checkSynonym(StandardDataDTO standardDataDTO) {
        //대표 동의어 Row도 조회해야함.
        //이 시점에 StandardDataDTO는 비표준 단어,
        //비 표준 단어("나")의 ALT_DIC_ID 는 표준 단어의 DIC_ID이므로
        //현재 시점에서 StandardDataDTO의 alt_dic_id를 들고
        //StandardDic을 조회한 후
        //resultStringBuilder 결과 String을 alt_dic_id에 할당하면됨.

        List<StandardDictionaryDTO> result = standardDataSearchMapper.checkSynonym(standardDataDTO);
        if(result == null){
            return null;
        }
        // StringBuilder를 사용하여 문자열을 효율적으로 빌드
        StringBuilder resultStringBuilder = new StringBuilder();
        for (StandardDictionaryDTO standardDictionaryDTO : result) {
            resultStringBuilder.append(standardDictionaryDTO.getDicLogNm());
            resultStringBuilder.append(",");  // 콤마 추가
        }

        // 마지막 콤마 제거
        if (resultStringBuilder.length() > 0) {
            resultStringBuilder.setLength(resultStringBuilder.length() - 1);
        }

        return createStandardDataWordDTO(standardDataSearchMapper.searchDictionaryDTOByDictionaryId(standardDataDTO),resultStringBuilder.toString());
    }
    private StandardDataDTO createStandardDataWordDTO(StandardDictionaryDTO dictionaryDTO,String synonym) {
        return StandardDataDTO.builder()
                .dicId(dictionaryDTO.getDicId())
                .standardClassification(dictionaryDTO.getStdAreaId())
                .synonym(synonym)
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
}