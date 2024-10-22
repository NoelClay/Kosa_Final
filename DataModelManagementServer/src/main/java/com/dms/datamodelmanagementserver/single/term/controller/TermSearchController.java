package com.dms.datamodelmanagementserver.single.term.controller;

import com.dms.datamodelmanagementserver.single.term.service.TermSearchService;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/dms")
public class TermSearchController {

    private final TermSearchService termSearchService;
    private final StandardAreaSelectListService standardAreaSelectListService;


    public TermSearchController(TermSearchService termSearchService, StandardAreaSelectListService standardAreaSelectListService) {
        this.termSearchService = termSearchService;
        this.standardAreaSelectListService = standardAreaSelectListService;
    }

    @GetMapping("/term")
    public String termInsertForm(HttpSession session, HttpServletRequest request) {

        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList", stdList);

        return "/term/termInsertPage"; // JSP 파일 이름을 리턴
    }

    @PostMapping("/single-term/searchWordRest")
    @ResponseBody
    public Map<String, Object> getWordListRest(@RequestBody Map<String, String> requestMap, Model model) {
        String stdAreaId = requestMap.get("stdAreaId");
        String searchWord = requestMap.get("searchWord");
        log.info("DMS :: TermListController= END = " + stdAreaId + "ㅡㅡ" + searchWord);

        List<WordDTO> wordList = termSearchService.selectWordListRest(stdAreaId, searchWord);

        log.info("DMS :: TermListController dicLogNmList= " + wordList);


        String[] searchWords = searchWord.split(" ");

        List<String> dicLogNmList = wordList.stream()
                .map(WordDTO::getDicLogNm)
                .flatMap(str -> Arrays.stream(str.split(" ")))
                .toList();

        log.info("DMS :: TermListController dicLogNmList= " + dicLogNmList);


        String[] dicLogNmCombined = dicLogNmList.toArray(new String[0]);

        List<String> listedSearchWords = Arrays.asList(searchWords);
        List<String> listedDicLogNmCombined = Arrays.asList(dicLogNmCombined);
        log.info("DMS :: TermListController listedSearchWords= " + listedSearchWords);
        log.info("DMS :: TermListController listedDicLogNmCombined= " + listedDicLogNmCombined);


        List<String> differenceWord = listedSearchWords.stream()
                .map(String::trim) // 각 요소의 앞뒤 공백 제거
                .filter(element -> !listedDicLogNmCombined.contains(element.trim()))// 리스트에 포함되어 있지 않은 값 필터링
                .toList();

        boolean isSearched = !wordList.isEmpty();

        Map<String, Object> response = new HashMap<>();

        response.put("differenceCombined", differenceWord);
        response.put("isSearched", isSearched);
        response.put("wordList", wordList);

        log.info("DMS :: TermListController= END = " + wordList);

        return response;
    }



}