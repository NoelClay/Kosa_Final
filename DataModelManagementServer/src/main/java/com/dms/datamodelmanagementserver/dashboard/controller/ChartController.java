package com.dms.datamodelmanagementserver.dashboard.controller;


import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.dashboard.dto.ChartDTO;
import com.dms.datamodelmanagementserver.dashboard.service.ChartService;
import com.dms.datamodelmanagementserver.global.session.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/dms/dashboard")
@RestController
@Slf4j
public class ChartController {
    private final LogDefault logDefault;
    private final ChartService chartService;

    public ChartController(LogDefault logDefault, ChartService chartService) {
        this.logDefault = logDefault;
        this.chartService = chartService;
    }

    @PostMapping(value = "/chart")
    public ResponseEntity<List<ChartDTO>> getChart(@RequestBody String standardAreaName, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        log.info("Test From FrontEnd ::: " + standardAreaName);
        List<ChartDTO> chartDTOList = chartService.getChart(standardAreaName, request);
        return ResponseEntity.status(HttpStatus.OK).body(chartDTOList);
    }
}