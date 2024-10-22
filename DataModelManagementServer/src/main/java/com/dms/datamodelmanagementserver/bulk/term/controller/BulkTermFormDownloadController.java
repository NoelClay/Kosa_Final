package com.dms.datamodelmanagementserver.bulk.term.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Controller
@RequestMapping("/dms")
public class BulkTermFormDownloadController {

	@GetMapping("/downloadExcelFormOfTerm")
    public ResponseEntity<Resource> downloadExcel() throws IOException {
        Resource resource = new ClassPathResource("static/downloads/bulk_term_insert.xlsx");

        InputStream inputStream = resource.getInputStream();
        
        String encodedFilename = URLEncoder.encode("용어_일괄등록(템플릿).xlsx", "UTF-8");
        encodedFilename = encodedFilename.replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\"")
                .body(resource);
    }
}
