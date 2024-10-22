package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.single.term.mapper.TermMapper;
import com.dms.datamodelmanagementserver.single.term.service.TermSearchService;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TermSearchServiceImpl implements TermSearchService {
	
	private final TermMapper termMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    @Autowired
    public TermSearchServiceImpl(TermMapper termMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.termMapper = termMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }
	
	@Override
	public List<WordDTO> selectWordListRest(String stdId, String searchWord) {
		List<WordDTO> wordDTOList = new ArrayList<>();

        WordDTO wordDTO = new WordDTO();
        wordDTO.setStdAreaId(stdId);

        String stdAreaId = null; // 변수 선언

        if (!stdId.isEmpty()) {
            stdAreaId = standardAreaSelectOneService.selectOne(wordDTO.getStdAreaId()).getStdAreaId();

            log.info("STD::TermSearchServiceImpl SEARCHED stdAreaId= " + stdAreaId);

            // stdAreaId 값을 이용한 추가 로직 수행
        } else {
            // 리스트가 비어있는 경우의 처리
        }

        // 먼저 전체 검색어가 DB에 존재하는지 확인
        List<WordDTO> fullSearchResult = termMapper.selectWordList(stdAreaId, searchWord);
        if (fullSearchResult != null && !fullSearchResult.isEmpty()) {
            wordDTOList.addAll(fullSearchResult); // 검색어가 DB에 존재하면 결과를 추가
            log.info("STD::TermSearchServiceImpl SEARCHED stdAreaId= " + stdAreaId);
            return wordDTOList; // 검색어가 존재하면 결과 반환
        }

        // 단어 구분자
        String[] words = searchWord.split(" ");
        int wordsLength = words.length;
        
        // 마지막 단어를 먼저 조회
        for (int i = words.length-1; i >= 1; i--) {
            StringBuilder remainedWordsBuilder = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                remainedWordsBuilder.append(words[j]).append(" ");
            }
            String remainedWords = remainedWordsBuilder.toString().trim();

            log.info("STD::TermSearchServiceImpl remainedWords= " + remainedWords);

            List<WordDTO> remainedWordsResult = termMapper.selectWordList(stdAreaId, remainedWords);
            if (remainedWordsResult != null && !remainedWordsResult.isEmpty()) {
                wordDTOList.addAll(remainedWordsResult);
                break;
            }
        }


        for (int i =0; i < words.length -1; i++){
            String singleWord = words[i];

            // 해당 단어로 DB 조회
            List<WordDTO> result = termMapper.selectWordList(stdAreaId, singleWord);
            if (result != null && !result.isEmpty()) {
                wordDTOList.addAll(result);
                log.info("STD::TermSearchServiceImpl Single Word= " + singleWord);
            }
        }

        // 로직 시작 - 역순으로 진행
        String firstLastWord = words[wordsLength - 1];

        List<WordDTO> firstLastWordResult = termMapper.selectWordList(stdAreaId, firstLastWord);
        if (firstLastWordResult != null && !firstLastWordResult.isEmpty()) {
            wordDTOList.addAll(firstLastWordResult);
        }
        return wordDTOList;
    }
}
