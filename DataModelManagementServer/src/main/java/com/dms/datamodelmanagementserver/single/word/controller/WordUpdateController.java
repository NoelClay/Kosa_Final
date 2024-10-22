package com.dms.datamodelmanagementserver.single.word.controller;


import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.service.WordUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class WordUpdateController {

    private final WordUpdateService wordUpdateService;

    public WordUpdateController(WordUpdateService wordUpdateService) {
        this.wordUpdateService = wordUpdateService;
    }

    @PostMapping("/single-word/updateWordRest")
    @ResponseBody
    public Map<String, Object> singleWordUpdateRest(@RequestBody WordDTO wordDTO) {

        boolean isWordUpdated = wordUpdateService.singleWordUpdateRest(wordDTO);

        log.info("DMS ::: WORD UPDATECONTROLLER START dicId = " + wordDTO);

        Map<String, Object> response = new HashMap<>();


        response.put("isWordUpdated", isWordUpdated);
        
        // 단어 업데이트 후, 다시 단어 정보를 조회
        response.put("dicId", wordDTO.getDicId());
        log.info("DMS ::: WORD UPDATECONTROLLER END isWordUpdated = " + isWordUpdated);

        return response;

    }
}
