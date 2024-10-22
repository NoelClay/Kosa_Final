package com.dms.standarddataserver.single.domain.controller;


import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.service.DomainDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/std")
@Slf4j
public class DomainDeleteController {
    private final LogDefault logDefault;
    private final DomainDeleteService domainDeleteService;

    public DomainDeleteController(LogDefault logDefault, DomainDeleteService domainDeleteService) {
        this.logDefault = logDefault;
        this.domainDeleteService = domainDeleteService;
    }

    @PostMapping(value = "/single-domain/delete")
    public ResponseEntity<Boolean> deleteDomain(@RequestBody DomainDTO domainDTO) {
        logDefault.logCurrentMethod();
        boolean isDomainDeleted = domainDeleteService.deleteDomain(domainDTO);
        return ResponseEntity.status(HttpStatus.OK).body(isDomainDeleted);
    }

}
