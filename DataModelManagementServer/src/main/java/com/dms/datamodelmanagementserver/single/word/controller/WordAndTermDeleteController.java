package com.dms.datamodelmanagementserver.single.word.controller;

import com.dms.datamodelmanagementserver.single.word.service.WordAndTermDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class WordAndTermDeleteController {

    private final WordAndTermDeleteService wordAndTermDeleteService;

    public WordAndTermDeleteController(WordAndTermDeleteService wordAndTermDeleteService) {
        this.wordAndTermDeleteService = wordAndTermDeleteService;
    }
    @PostMapping("/single-wordAndTerm/deleteRest")
    @ResponseBody
    public Map<String, Object> deleteWordAndTermRest(@RequestBody Map<String, String> requestMap) {

        String deleteDicId = requestMap.get("dicId");

        boolean isWordAndTermDeleted = wordAndTermDeleteService.deleteWordAndTermRest(deleteDicId);

        Map<String, Object> response = new HashMap<>();

        response.put("isWordAndTermDeleted", isWordAndTermDeleted);

        return response;

    }

}
