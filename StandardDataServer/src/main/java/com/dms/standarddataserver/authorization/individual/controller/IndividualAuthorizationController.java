package com.dms.standarddataserver.authorization.individual.controller;

import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationByStdjAreaIdDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualAuthorizationDetailDTO;
import com.dms.standarddataserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.standarddataserver.authorization.individual.dto.SubjAreaDetailDTO;
import com.dms.standarddataserver.authorization.individual.service.IndividualAuthorizationService;
import com.dms.standarddataserver.global.LogDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/std/authorization-individual/")
public class IndividualAuthorizationController {

    private final IndividualAuthorizationService individualAuthorizationService;
    private final LogDefault logDefault;


    public IndividualAuthorizationController(IndividualAuthorizationService individualAuthorizationService,
			LogDefault logDefault) {
		this.individualAuthorizationService = individualAuthorizationService;
		this.logDefault = logDefault;
	}

	@PostMapping("list")
    private ResponseEntity<?> getAllIndividualAuthorizationList() {
        logDefault.logCurrentMethod();
        List<IndividualUserDTO> individualUserDtoList = individualAuthorizationService.getAllIndividualAuthorizationList();
        return ResponseEntity.status(HttpStatus.OK).body(individualUserDtoList);
    }
	
	@PostMapping("detail")
    private ResponseEntity<?> findIndividualAuthorizationDetailById(@RequestBody String usrId) {
        logDefault.logCurrentMethod();
        IndividualAuthorizationDetailDTO individualAuthorizationDetailDto = individualAuthorizationService.findIndividualAuthorizationDetailById(usrId);
        return ResponseEntity.status(HttpStatus.OK).body(individualAuthorizationDetailDto);
    }
	
	@PostMapping("insert")
    private ResponseEntity<?> insertIndividualDetailAndAuthorization(@RequestBody IndividualAuthorizationDetailDTO individualAuthorizationDetailDTO) {
        logDefault.logCurrentMethod();
        boolean insertResult = individualAuthorizationService.insertIndividualDetailAndAuthorization(individualAuthorizationDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body(insertResult);
    }

	@PostMapping("all-subjarea")
    private ResponseEntity<?> getAllSubjAreaList() {
        logDefault.logCurrentMethod();
        List<SubjAreaDetailDTO> subjAreaDetailDTOList= individualAuthorizationService.getAllSubjAreaList();
        return ResponseEntity.status(HttpStatus.OK).body(subjAreaDetailDTOList);
    }

	@PostMapping("update")
    private ResponseEntity<?> updateIndividualAuthorization(@RequestBody List<IndividualAuthorizationByStdjAreaIdDTO> individualAuthorizationByStdjAreaIdDTOList) {
        logDefault.logCurrentMethod();
        boolean isUpdateState = individualAuthorizationService.updateIndividualAuthorization(individualAuthorizationByStdjAreaIdDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(isUpdateState);
    }
	
	@PostMapping("search-user")
    private ResponseEntity<?> getAllUsersBySearch(@RequestBody SearchForUserDTO searchForUserDTO) {
		logDefault.logCurrentMethod();
    	List<IndividualUserDTO> searchedUserList = individualAuthorizationService.getAllUsersBySearch(searchForUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(searchedUserList);
    }
	
	@PostMapping("validation-userId")
    private ResponseEntity<?> validateUserId(@RequestBody String userId) {
		logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(individualAuthorizationService.validateUserId(userId));
    }
	
	@PostMapping("update-user-info")
    private ResponseEntity<?> deleteUser(@RequestBody IndividualUserDTO individualUserDTO) {
        logDefault.logCurrentMethod();
        boolean isUpdateState = individualAuthorizationService.updateUser(individualUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(isUpdateState);
    }
	
//	@PostMapping("delete")
//    private ResponseEntity<?> deleteUser(@RequestBody String userId) {
//        logDefault.logCurrentMethod();
//        boolean isDeleteState = individualAuthorizationService.deleteUser(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(isDeleteState);
//    }
}
