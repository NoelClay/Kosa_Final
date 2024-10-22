package com.dms.datamodelmanagementserver.single.word.controller;

import com.dms.datamodelmanagementserver.single.word.service.WordService;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Controller
@RequestMapping("/dms")
public class WordInsertController {

    private final WordService wordService;
    private final StandardAreaSelectListService standardAreaSelectListService;


    public WordInsertController(WordService wordService, StandardAreaSelectListService standardAreaSelectListService) {
        this.wordService = wordService;
        this.standardAreaSelectListService = standardAreaSelectListService;
    }


    @GetMapping("/single-word/form")
    public String wordInsertForm(HttpSession session, HttpServletRequest request) {
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList", stdList);

        return "/word/wordInsertPage"; // JSP 파일 이름을 리턴
    }

    @PostMapping("/single-word/insertRest")
    @ResponseBody
    public Map<String, Boolean> singleWordInsertRest(@RequestBody WordDTO wordDTO) {
        log.info("DMS::INSERT CONTROLLER" + wordDTO);



        wordDTO.setDicId(UUID.randomUUID().toString());
        wordDTO.setDicGbnCd("word");
        wordDTO.setStandardYn("Y");

        boolean isWordInserted = wordService.singleWordInsertRest(wordDTO);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isWordInserted", isWordInserted);
        log.info("DMS::INSERT CONTROLLER END");

        return response;
    }

}


