package com.dms.standarddataserver.standardArea.controller;

import com.dms.standarddataserver.global.LogDefault;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandardDataController {
    private final LogDefault logDefault;

    public StandardDataController(LogDefault logDefault) {
        this.logDefault = logDefault;
    }
}
