package com.dms.datamodelmanagementserver.single.word.controller;

import com.dms.datamodelmanagementserver.single.word.service.WordDuplicateService;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class WordDuplicateController {

    private final WordDuplicateService wordDuplicateService;

    public WordDuplicateController(WordDuplicateService wordDuplicateService) {
		super();
		this.wordDuplicateService = wordDuplicateService;
	}

	@PostMapping("/single-word/duplicateRest")
    @ResponseBody
    public Map<String, Boolean> isDuplicateRest(@RequestBody WordDTO wordDTO) {

        boolean isDuplicate = wordDuplicateService.isDuplicateRest(wordDTO);

        log.info("DMS::WordDuplicateController= " + isDuplicate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return response;
    }

}


