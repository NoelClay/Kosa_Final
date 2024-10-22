package com.dms.standarddataserver.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.standarddataserver.member.dto.MemberDTO;
import com.dms.standarddataserver.member.service.MemberLoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/std/user")
public class MemberLoginController {
    private final MemberLoginService memberLoginService;

    @Autowired
    public MemberLoginController(MemberLoginService memberLoginService) {
        this.memberLoginService = memberLoginService;
    }

    @PostMapping("/login")
    private ResponseEntity<?> authenticateMember(@RequestBody MemberDTO memberDTO) {
        log.info(memberDTO.getId()+memberDTO.getName()+memberDTO.getPassword());

        MemberDTO resultMemberDTO = memberLoginService.authenticateMember(memberDTO);
        if (resultMemberDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultMemberDTO);
        }
    }
    
    @PostMapping("/loginSession")
    private ResponseEntity<?> loginSession(@RequestBody MemberDTO memberDTO) {
    	MemberDTO resultMemberDTO = memberLoginService.loginSession(memberDTO);
        if (resultMemberDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultMemberDTO);
        }
    }

}
