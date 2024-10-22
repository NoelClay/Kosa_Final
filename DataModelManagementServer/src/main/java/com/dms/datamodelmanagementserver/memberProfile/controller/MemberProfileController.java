package com.dms.datamodelmanagementserver.memberProfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dms.datamodelmanagementserver.global.LogDefault;

@Controller
@RequestMapping("/dms/member-profile/")
public class MemberProfileController {
	
	private final LogDefault logDefault;
	
	public MemberProfileController(LogDefault logDefault) {
		this.logDefault = logDefault;
	}

	@GetMapping("form")
	public String getMemberProfileForm() {
		logDefault.logCurrentMethod();
		return "/member-profile/member-profile-form";
	}

}
