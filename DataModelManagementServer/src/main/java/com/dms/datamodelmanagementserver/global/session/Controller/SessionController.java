package com.dms.datamodelmanagementserver.global.session.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.session.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/dms/session")
public class SessionController {
    private final LogDefault logDefault;
    private final SessionService sessionService;

    public SessionController(LogDefault logDefault, SessionService sessionService) {
        this.logDefault = logDefault;
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/get")
    public ResponseEntity<String> getSession(HttpServletRequest request) {
        logDefault.logCurrentMethod();
        String selectedStandardArea = sessionService.getSession(request);
        return ResponseEntity.status(HttpStatus.OK).body(selectedStandardArea);
    }

    @PostMapping(value = "/set")
    public ResponseEntity<String> setSession(@RequestBody String selectedStandardArea, HttpSession session, HttpServletResponse response) {
        logDefault.logCurrentMethod();
        sessionService.setSession(selectedStandardArea.replaceAll("\"", ""), session, response);
        return ResponseEntity.status(HttpStatus.OK).body(selectedStandardArea.replaceAll("\"", ""));
    }
}
