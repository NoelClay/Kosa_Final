package com.dms.datamodelmanagementserver.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
	
	@GetMapping(value = "/")
    public String welcomePage() {
        return "redirect:/dms/";
    }

}
