package com.dms.standarddataserver.standardData.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataSearchDTO;
import com.dms.standarddataserver.standardData.dto.StandardDictionaryDTO;
import com.dms.standarddataserver.standardData.dto.StandardDomainDTO;
import com.dms.standarddataserver.standardData.mapper.StandardDataSearchMapper;
import com.dms.standarddataserver.standardData.service.StandardDataSearchService;
import com.google.common.base.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StandardDataSearchServiceImpl implements StandardDataSearchService {
    private final StandardDataSearchMapper standardDataSearchMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public StandardDataSearchServiceImpl(StandardDataSearchMapper standardDataSearchMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.standardDataSearchMapper = standardDataSearchMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<StandardDataDTO> search(StandardDataSearchDTO standardDataSearchDTO) {
        StandardAreaDTO standardAreaDTO = standardAreaSelectOneService.selectOne(standardDataSearchDTO.getStandardAreaName());
        String standardAreaId = standardAreaDTO.getStdAreaId();
        List<StandardDictionaryDTO> standardDictionaryDTOList = standardDataSearchMapper.searchDictionaryByStandardArea(standardAreaId, standardDataSearchDTO);
        List<StandardDataDTO> standardDataDTOList = new ArrayList<>();
        String searchWord = standardDataSearchDTO.getSearchWord();

        if (standardDataSearchDTO.isWord()) {
            // 단어
            if (standardDataSearchDTO.isEntity() && standardDataSearchDTO.isAttribute()) {
                // 속성 분류어와 엔터티 분류어 인 경우만 조회
                for (StandardDictionaryDTO standardDictionaryDTO : standardDictionaryDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (standardDictionaryDTO.getDicGbnCd().equals("word") && standardDictionaryDTO.getAttrClssYn().equals("Y") && standardDictionaryDTO.getEntClssYn().equals("Y")) {
                        if (!searchWord.isEmpty() && !standardDictionaryDTO.getDicLogNm().contains(searchWord))
                            continue;
                        standardDataDTOList.add(createStandardDataWordDTO(standardDictionaryDTO, standardAreaDTO, standardDataSearchMapper));
                    }
                }
            } else if (standardDataSearchDTO.isAttribute()) {
                // 속성 분류어만 조회
                for (StandardDictionaryDTO standardDictionaryDTO : standardDictionaryDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (standardDictionaryDTO.getDicGbnCd().equals("word") && standardDictionaryDTO.getAttrClssYn().equals("Y")) {
                        if (!searchWord.isEmpty() && !standardDictionaryDTO.getDicLogNm().contains(searchWord))
                            continue;
                        standardDataDTOList.add(createStandardDataWordDTO(standardDictionaryDTO, standardAreaDTO, standardDataSearchMapper));

                    }
                }
            } else if (standardDataSearchDTO.isEntity()) {
                // 엔터티 분류어만 조회
                for (StandardDictionaryDTO standardDictionaryDTO : standardDictionaryDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (standardDictionaryDTO.getDicGbnCd().equals("word") && standardDictionaryDTO.getEntClssYn().equals("Y")) {
                        if (!searchWord.isEmpty() && !standardDictionaryDTO.getDicLogNm().contains(searchWord))
                            continue;
                        standardDataDTOList.add(createStandardDataWordDTO(standardDictionaryDTO, standardAreaDTO, standardDataSearchMapper));
                    }
                }
            } else {
                // 모두 조회
                for (StandardDictionaryDTO standardDictionaryDTO : standardDictionaryDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (standardDictionaryDTO.getDicGbnCd().equals("word")) {
                        if (!searchWord.isEmpty() && !standardDictionaryDTO.getDicLogNm().contains(searchWord))
                            continue;
                        standardDataDTOList.add(createStandardDataWordDTO(standardDictionaryDTO, standardAreaDTO, standardDataSearchMapper));
                    }
                }
            }
        }
        if (standardDataSearchDTO.isDomain()) {
            //도메인
            //이 시점에 도메인 리스트가 조회됨.
            //None이 아니면 ,  그럼 둘 다 핏이 맞아야함.
            if (!standardDataSearchDTO.getDomainType().equals("None") && !standardDataSearchDTO.getDataType().equals("None")) {
                List<StandardDomainDTO> standardDomainDTOList = standardDataSearchMapper.searchStandardDomainDTOListByDomainTypeAndDataType(standardAreaId, standardDataSearchDTO.getDomainType(), standardDataSearchDTO.getDataType());

                for (StandardDomainDTO standardDomainDTO : standardDomainDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (!searchWord.isEmpty() && !standardDomainDTO.getDomNm().contains(searchWord))
                        continue;
                    standardDataDTOList.add(createStandardDataDomainDTO(standardDomainDTO, standardAreaDTO));
                }
            } else if (!standardDataSearchDTO.getDomainType().equals("None") && standardDataSearchDTO.getDataType().equals("None")) {
                List<StandardDomainDTO> standardDomainDTOList = standardDataSearchMapper.searchStandardDomainDTOListByDomainType(standardAreaId, standardDataSearchDTO.getDomainType());

                for (StandardDomainDTO standardDomainDTO : standardDomainDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (!searchWord.isEmpty() && !standardDomainDTO.getDomNm().contains(searchWord))
                        continue;
                    standardDataDTOList.add(createStandardDataDomainDTO(standardDomainDTO, standardAreaDTO));
                }
            } else if (standardDataSearchDTO.getDomainType().equals("None") && !standardDataSearchDTO.getDataType().equals("None")) {
                List<StandardDomainDTO> standardDomainDTOList = standardDataSearchMapper.searchStandardDomainDTOListByDataType(standardAreaId, standardDataSearchDTO.getDataType());
                for (StandardDomainDTO standardDomainDTO : standardDomainDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (!searchWord.isEmpty() && !standardDomainDTO.getDomNm().contains(searchWord))
                        continue;
                    standardDataDTOList.add(createStandardDataDomainDTO(standardDomainDTO, standardAreaDTO));
                }
            } else {
                List<StandardDomainDTO> standardDomainDTOList = standardDataSearchMapper.searchStandardDomainDTOList(standardAreaId);
                for (StandardDomainDTO standardDomainDTO : standardDomainDTOList) {
                    if (standardDataDTOList.size() >= 100) {
                        break;
                    }
                    if (!searchWord.isEmpty() && !standardDomainDTO.getDomNm().contains(searchWord))
                        continue;
                    standardDataDTOList.add(createStandardDataDomainDTO(standardDomainDTO, standardAreaDTO));
                }
            }
        }
        if (standardDataSearchDTO.isTerm()) {
            //용어인 것을 조회
            for (StandardDictionaryDTO standardDictionaryDTO : standardDictionaryDTOList) {
                if (standardDataDTOList.size() >= 100) {
                    break;
                }
                if (standardDictionaryDTO.getDicGbnCd().equals("term")) {
                    if (!searchWord.isEmpty() && !standardDictionaryDTO.getDicLogNm().contains(searchWord))
                        continue;
                    StandardDomainDTO standardDomainDTO = null;
                    try {
                        standardDomainDTO = standardDataSearchMapper.searchStandardDomainDTOByDomainId(standardDictionaryDTO.getDomId());
                        if (standardDomainDTO == null) continue;
                    } catch (Exception e) {
                        System.err.println("Error processing standard domain: " + e.getMessage());
                        continue;
                    }
                    standardDataDTOList.add(createStandardDataTermDTO(standardDictionaryDTO, standardAreaDTO, standardDomainDTO));
                }
            }
        }
        return standardDataDTOList;
    }

    private StringBuilder relatedTermBuilder(String division, String logicalName, String standardAreaId) {
        List<String> relatedTermList = null;
        if (division.equals("word")) {
            relatedTermList = standardDataSearchMapper.selectRelatedTermByLogicalName(logicalName, standardAreaId);
        } else if (division.equals("domain")) {
            relatedTermList = standardDataSearchMapper.selectRelatedTermByDomainId(logicalName, standardAreaId);
        }

        StringBuilder resultStringBuilder = new StringBuilder();
        for (String relatedTerm : relatedTermList) {
            resultStringBuilder.append(relatedTerm);
            resultStringBuilder.append(",");  // 콤마 추가
        }

        // 마지막 콤마 제거
        if (resultStringBuilder.length() > 0) {
            resultStringBuilder.setLength(resultStringBuilder.length() - 1);
        }
        return resultStringBuilder;
    }

    private StandardDataDTO createStandardDataWordDTO(StandardDictionaryDTO dictionaryDTO, StandardAreaDTO areaDTO, StandardDataSearchMapper standardDataSearchMapper) {
        /*
            이 시점에 연관 용어 리스트를 한 String 으로 합쳐야함.
         */
        StringBuilder resultStringBuilder = relatedTermBuilder(dictionaryDTO.getDicGbnCd(), dictionaryDTO.getDicLogNm(), areaDTO.getStdAreaId());
        
        String synonymList = standardDataSearchMapper.getSynonymList(areaDTO.getStdAreaId()).stream()
								    .filter(it -> it.getAltDicId().equals(dictionaryDTO.getDicId()))
								    .map(it -> it.getDicLogNm())
								    .collect(Collectors.joining(" , "));     

        return StandardDataDTO.builder()
                .dicId(dictionaryDTO.getDicId())
                .standardClassification(areaDTO.getStdAreaNm())
                .synonym(synonymList)
                .division(dictionaryDTO.getDicGbnCd())
                .logicalName(dictionaryDTO.getDicLogNm())
                .physicalName(dictionaryDTO.getDicPhyNm())
                .physicalFullName(dictionaryDTO.getDicPhyFllNm())
                .isStandard(dictionaryDTO.getStandardYn())
                .attributeClassifier(dictionaryDTO.getAttrClssYn())
                .entityClassifier(dictionaryDTO.getEntClssYn())
                .description(dictionaryDTO.getDicDesc())
                .relatedTerm(resultStringBuilder.toString())
                .build();
    }

    private StandardDataDTO createStandardDataTermDTO(StandardDictionaryDTO dictionaryDTO, StandardAreaDTO areaDTO, StandardDomainDTO standardDomainDTO) {
        /*
    번호 표준분류 구분 논리명 물리명 [ 도메인 유형 , 도메인 명 , 논리 데이터 타입 , 길이 ] <- 가지고있는 DOM_ID로 찾아야함 , 표준 여부
     */

        return StandardDataDTO.builder()
                .dicId(dictionaryDTO.getDicId())
                .standardClassification(areaDTO.getStdAreaNm())
                .division(dictionaryDTO.getDicGbnCd())
                .logicalName(dictionaryDTO.getDicLogNm())
                .physicalName(dictionaryDTO.getDicPhyNm())
                .isStandard(dictionaryDTO.getStandardYn())
                .description(dictionaryDTO.getDicDesc())
                .domainType(standardDomainDTO.getDomTypeCd())
                .domainName(standardDomainDTO.getDomNm())
                .logicalDataType(standardDomainDTO.getDataTypeCd())
                .length(standardDomainDTO.getDataLen())
                .description(dictionaryDTO.getDicDesc())
                .build();
    }

    private StandardDataDTO createStandardDataDomainDTO(StandardDomainDTO standardDomainDTO, StandardAreaDTO areaDTO) {
        String domainGroupName = standardDataSearchMapper.selectDomainGroupByDomainGroupId(standardDomainDTO.getDomGrpId());

        StringBuilder resultStringBuilder = relatedTermBuilder("domain", standardDomainDTO.getDomId(), areaDTO.getStdAreaId());

        return StandardDataDTO.builder()
                .dicId(standardDomainDTO.getDomId())
                .standardClassification(areaDTO.getStdAreaNm())
                .division("domain")
                .logicalName(standardDomainDTO.getDomNm())
                .keyDomain(standardDomainDTO.getKeyDomNm())
                .domainType(standardDomainDTO.getDomTypeCd())
                .domainName(standardDomainDTO.getDomNm())
                .domainGroup(domainGroupName)
                .logicalDataType(standardDomainDTO.getDataTypeCd())
                .length(standardDomainDTO.getDataLen())
                .scale(standardDomainDTO.getDataScale())
                .isStandard("Y")
                .description(standardDomainDTO.getDomDesc())
                .dataMin(standardDomainDTO.getDataMin())
                .dataMax(standardDomainDTO.getDataMax())
                .relatedTerm(resultStringBuilder.toString())
                .build();
    }
}
