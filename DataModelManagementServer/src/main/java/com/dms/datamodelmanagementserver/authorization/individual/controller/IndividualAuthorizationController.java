package com.dms.datamodelmanagementserver.authorization.individual.controller;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthUpdateRequestDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthorizationByStdjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualAuthorizationDetailDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SearchForUserDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.SubjAreaDetailDTO;
import com.dms.datamodelmanagementserver.authorization.individual.dto.UpdateUserPasswordDTO;
import com.dms.datamodelmanagementserver.authorization.individual.service.IndividualAuthorizationService;
import com.dms.datamodelmanagementserver.global.LogDefault;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dms/authorization-individual/")
public class IndividualAuthorizationController {

    private final IndividualAuthorizationService individualAuthorizationService;
    private final LogDefault logDefault;
    
    private final String UPDATE_FAILURE = "updateFailure";
    private final String UPDATE_SUCCESS = "updateSuccess";
    private final String VALIDATE_PASSWORD_INCORRECT = "incorrect_password";
 
	public IndividualAuthorizationController(IndividualAuthorizationService individualAuthorizationService,
			LogDefault logDefault) {
		this.individualAuthorizationService = individualAuthorizationService;
		this.logDefault = logDefault;
	}

	@GetMapping("list")
    public String getForm(HttpSession session, Model model) {
        logDefault.logCurrentMethod();
        List<IndividualUserDTO> individualUserDtoList = individualAuthorizationService.getAllIndividualAuthorizationList();
        model.addAttribute("individualUserDtoList", individualUserDtoList);
        return "/authorization/individual";
    }
	
    @PostMapping("detail")
    @ResponseBody
    public IndividualAuthorizationDetailDTO findIndividualAuthorizationDetailById(@RequestBody String usrId) {
        logDefault.logCurrentMethod();
        return individualAuthorizationService.findIndividualAuthorizationDetailById(usrId);
    }
    
    @PostMapping("insert")
    @ResponseBody
    public Map<String, String> insertIndividualDetailAndAuthorization(@RequestBody IndividualAuthorizationDetailDTO individualAuthorizationDetailDTO) {
    	Map<String, String> result = new HashMap<>();
        logDefault.logCurrentMethod();
        if (individualAuthorizationService.insertIndividualDetailAndAuthorization(individualAuthorizationDetailDTO)) {
        	result.put("usrId", individualAuthorizationDetailDTO.getIndividualUserDTO().getUsrId());
        }
        return result;
    }
    
    @GetMapping("all-subjarea")
    @ResponseBody
    public List<SubjAreaDetailDTO> getAllSubjAreaList() {
        logDefault.logCurrentMethod();
        return individualAuthorizationService.getAllSubjAreaList();
    }

    @PostMapping("update")
    @ResponseBody
    public String updateIndividualAuthorization(@RequestBody IndividualAuthUpdateRequestDTO individualAuthUpdateRequestDTO) {
        logDefault.logCurrentMethod();
        if(individualAuthorizationService.updateIndividualAuthorization(individualAuthUpdateRequestDTO)) {
        	return individualAuthUpdateRequestDTO.getUsrId();
        }
        return UPDATE_FAILURE;
    }
    
    @PostMapping("search-user")
    @ResponseBody
    public List<IndividualUserDTO> getAllUsersBySearch(@RequestBody SearchForUserDTO searchForUserDTO) {
    	logDefault.logCurrentMethod();
    	List<IndividualUserDTO> searchedUserList = individualAuthorizationService.getAllUsersBySearch(searchForUserDTO);
    	return searchedUserList;
    }
    
    @PostMapping("validation-userId")
    @ResponseBody
    public boolean validateUserId(@RequestBody String userId) {
    	logDefault.logCurrentMethod();
    	boolean result = individualAuthorizationService.validateUserId(userId);
    	return result;
    }
    
    @PostMapping("update-user-info")
    @ResponseBody
    public Map<String, String> updateUser(@RequestBody IndividualUserDTO individualUserDTO) {
    	Map<String, String> result = new HashMap<>();
        logDefault.logCurrentMethod();
        boolean isUpdateState = individualAuthorizationService.updateUser(individualUserDTO);
        result.put("isUpdateState", isUpdateState ? UPDATE_SUCCESS : UPDATE_FAILURE);
        return result;
    }
    
    @PostMapping("update-user-password")
    @ResponseBody
    public String updateUserPassword(@RequestBody UpdateUserPasswordDTO updateUserPasswordDTO) {
        logDefault.logCurrentMethod();
        boolean isUpdateState = individualAuthorizationService.updateUserPassword(updateUserPasswordDTO);
        return isUpdateState ? UPDATE_SUCCESS : VALIDATE_PASSWORD_INCORRECT;
    }
    
    @PostMapping("user-detail")
    @ResponseBody
    public IndividualUserDTO findIndividualUserById(@RequestBody String userId) {
    	return individualAuthorizationService.findIndividualUserById(userId);
    }

}
