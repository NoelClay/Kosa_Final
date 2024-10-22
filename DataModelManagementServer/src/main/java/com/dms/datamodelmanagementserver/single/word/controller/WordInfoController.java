package com.dms.datamodelmanagementserver.single.word.controller;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.service.WordInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class WordInfoController {

    private final WordInfoService wordInfoService;

    public WordInfoController(WordInfoService wordInfoService) {
        this.wordInfoService = wordInfoService;
    }


    @PostMapping("/single-wordAndTerm/selectRest")
    @ResponseBody
    public Map<String, Object> getWordAndTermInfoByDicId(@RequestBody Map<String, String> requestMap) {


        String dicId = requestMap.get("dicId");

        log.info("DMS :: WordInfoController= START = " + dicId );

        WordDTO receivedWordAndTermDTO = wordInfoService.getWordAndTermInfoRest(dicId);


        Map<String, Object> response = new HashMap<>();

        response.put("receivedWordAndTermDTO", receivedWordAndTermDTO);
        response.put("standardYn", receivedWordAndTermDTO.getStandardYn());
        log.info("DMS :: WordInfoController= END = " + receivedWordAndTermDTO.getStandardYn());

        log.info("DMS :: WordInfoController= END = " + receivedWordAndTermDTO );


//        boolean isFound = !receivedWordDTO.isEmpty();



        return response;
    }



}
