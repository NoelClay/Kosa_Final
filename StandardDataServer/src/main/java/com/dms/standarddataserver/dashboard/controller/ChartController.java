package com.dms.standarddataserver.dashboard.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.dashboard.dto.ChartDTO;
import com.dms.standarddataserver.dashboard.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/std/dashboard")
@Slf4j
@RestController
public class ChartController {
    private final LogDefault logDefault;
    private final ChartService chartService;

    public ChartController(LogDefault logDefault, ChartService chartService) {
        this.logDefault = logDefault;
        this.chartService = chartService;
    }

    @PostMapping(value = "/chart")
    public ResponseEntity<List<ChartDTO>> getChart(@RequestBody String standardAreaName) {
        logDefault.logCurrentMethod();
        List<ChartDTO> chartDTOList = chartService.getChart(standardAreaName);
        return ResponseEntity.status(HttpStatus.OK).body(chartDTOList);
    }
}
