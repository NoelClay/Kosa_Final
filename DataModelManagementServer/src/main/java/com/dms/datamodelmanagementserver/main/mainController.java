package com.dms.datamodelmanagementserver.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class mainController {
	

	@GetMapping(value = "/")
    public String welcomePage() {

        log.info("이거 ㅗ디나?");
        return "redirect:/dms/";
    }

}
