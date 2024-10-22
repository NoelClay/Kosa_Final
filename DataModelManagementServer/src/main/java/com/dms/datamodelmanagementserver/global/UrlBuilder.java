package com.dms.datamodelmanagementserver.global;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlBuilder {

    // private static final String BASE_URL1 = "http://localhost:8081"; 
    // private static final String BASE_URL2 = "http://localhost:8082"; 
    // private static final String BASE_URL1 = "http://10.178.0.9"; 
    // private static final String BASE_URL2 = "http://10.178.0.9:39200"; 
        private static final String BASE_URL1 = "http://192.168.240.119:8081"; 
        private static final String BASE_URL2 = "http://192.168.240.119:8082"; 
	

    public String buildServiceUrl(String path) {
        String baseUrl;

        if (path.startsWith("/dms")) {
            baseUrl = BASE_URL1;
        } else if (path.startsWith("/std")) {
            baseUrl = BASE_URL2;
        } else {
            baseUrl = BASE_URL1;
        }

        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(path) 
                .toUriString(); 
    }
}

