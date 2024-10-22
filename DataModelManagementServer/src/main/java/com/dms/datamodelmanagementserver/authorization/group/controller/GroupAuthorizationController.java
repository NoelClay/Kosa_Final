package com.dms.datamodelmanagementserver.authorization.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthUpdateRequestDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationBySubjAreaIdDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupAuthorizationDetailDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.GroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.SearchForGroupDTO;
import com.dms.datamodelmanagementserver.authorization.group.dto.UserSearchRequestDTO;
import com.dms.datamodelmanagementserver.authorization.group.service.GroupAuthorizationService;
import com.dms.datamodelmanagementserver.authorization.individual.dto.IndividualUserDTO;
import com.dms.datamodelmanagementserver.global.LogDefault;

@Controller
@RequestMapping("/dms/authorization-group/")
public class GroupAuthorizationController {
	
	private final GroupAuthorizationService groupAuthorizationService; 
	private final LogDefault logDefault;
	
	private final String UPDATE_FAILURE = "updateFailure";
	private final String INSERT_FAILURE = "insertFailure";

	public GroupAuthorizationController(GroupAuthorizationService groupAuthorizationService, LogDefault logDefault) {
		this.groupAuthorizationService = groupAuthorizationService;
		this.logDefault = logDefault;
	}
	
	@PostMapping("insert-group")
    @ResponseBody
    public String insertIndividualDetailAndAuthorization(@RequestBody GroupAuthorizationDetailDTO groupAuthorizationDetailDTO) {
        logDefault.logCurrentMethod();
        if (groupAuthorizationService.insertIndividualDetailAndAuthorization(groupAuthorizationDetailDTO)) {
        	return groupAuthorizationDetailDTO.getGroupDTO().getGroupId();
        }
        return INSERT_FAILURE;
    }

	@GetMapping("list")
	public String getAllGroupList(Model model){
		logDefault.logCurrentMethod();
		List<GroupDTO> groupDTOList = groupAuthorizationService.getAllGroupList();
		model.addAttribute("groupDTOList", groupDTOList);
        return "/authorization/group";
	}
	
	@PostMapping("detail")
    @ResponseBody
    public GroupAuthorizationDetailDTO findGroupAuthorizationDetailById(@RequestBody String groupId) {
        logDefault.logCurrentMethod();
        return groupAuthorizationService.findGroupAuthorizationDetailById(groupId);
    }
	
	@PostMapping("update-auth")
    @ResponseBody
    public String updateGroupAuthorization(@RequestBody GroupAuthUpdateRequestDTO groupAuthUpdateRequestDTO) {
        logDefault.logCurrentMethod();
        if(groupAuthorizationService.updateGroupAuthorization(groupAuthUpdateRequestDTO)) {
        	return groupAuthUpdateRequestDTO.getGroupId();
        }
        return UPDATE_FAILURE;
    }
	
	@PostMapping("validation-groupId")
    @ResponseBody
    public boolean validateGroupId(@RequestBody String groupId) {
    	return groupAuthorizationService.validateGroupId(groupId);
    }
	
	@PostMapping("validation-groupName")
    @ResponseBody
    public boolean validateGroupName(@RequestBody String groupName) {
    	return groupAuthorizationService.validateGroupName(groupName);
    }

	@PostMapping("search-userName")
    @ResponseBody
    public List<IndividualUserDTO> searchIndividualByName(@RequestBody UserSearchRequestDTO userSearchRequestDTO){
		return groupAuthorizationService.searchIndividualByName(userSearchRequestDTO);
	}
	
	@PostMapping("search-group")
    @ResponseBody
	public List<GroupDTO> findGroupBySearchCondition(@RequestBody SearchForGroupDTO searchForGroupDTO){
		return groupAuthorizationService.findGroupBySearchCondition(searchForGroupDTO);
	}
	
    @PostMapping("update-group-info")
    @ResponseBody
    public boolean updateGroup(@RequestBody GroupDTO groupDTO) {
        logDefault.logCurrentMethod();
        return groupAuthorizationService.updateGroup(groupDTO);
    }
	
	@PostMapping("delete-individual")
    @ResponseBody
	public boolean deleteIndividualFromGroup(@RequestBody UserSearchRequestDTO userSearchRequestDTO){
		return groupAuthorizationService.deleteIndividualFromGroup(userSearchRequestDTO);
	}
	
	@PostMapping("insert-individual")
    @ResponseBody
	public boolean insertIndividualToGroup(@RequestBody UserSearchRequestDTO userSearchRequestDTO){
		return groupAuthorizationService.insertIndividualToGroup(userSearchRequestDTO);
	}
}
