package com.dms.standarddataserver.authorization.group.controller;

import com.dms.standarddataserver.authorization.group.dto.GroupAuthorizationDetailDTO;
import com.dms.standarddataserver.authorization.group.dto.GroupDTO;
import com.dms.standarddataserver.authorization.group.service.GroupAuthorizationService;
import com.dms.standarddataserver.global.LogDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/std/authorization-group/")
public class GroupAuthorizationController {

    private final GroupAuthorizationService groupAuthorizationService;
    private final LogDefault logDefault;
    
    public GroupAuthorizationController(GroupAuthorizationService groupAuthorizationService, LogDefault logDefault) {
		this.groupAuthorizationService = groupAuthorizationService;
		this.logDefault = logDefault;
	}

	@PostMapping("list")
    private ResponseEntity<?> getAllGroupList() {
        logDefault.logCurrentMethod();
        List<GroupDTO> GroupDtoList = groupAuthorizationService.getAllGroupList();
        return ResponseEntity.status(HttpStatus.OK).body(GroupDtoList);
    }
	
	@PostMapping("detail")
    private ResponseEntity<?> findGroupAuthorizationDetailById(@RequestBody String groupId) {
        logDefault.logCurrentMethod();
        GroupAuthorizationDetailDTO groupAuthorizationDetailDto = groupAuthorizationService.findGroupAuthorizationDetailById(groupId);
        return ResponseEntity.status(HttpStatus.OK).body(groupAuthorizationDetailDto);
    }
}
