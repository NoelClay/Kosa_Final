package com.dms.datamodelmanagementserver.global;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlBuilder {

//    private static final String BASE_URL1 = "http://localhost:8081"; 
//    private static final String BASE_URL2 = "http://localhost:8082"; 
//    private static final String BASE_URL1 = "http://dms:8081"; 
//    private static final String BASE_URL2 = "http://std:8082"; 
//바로 아래 2줄 원본
    // private static final String BASE_URL1 = "http://dmstest:8081"; 
    // private static final String BASE_URL2 = "http://stdtest:8082"; 
    // private static final String BASE_URL1 = "http://ct_dms:8081"; 
    // private static final String BASE_URL2 = "http://ct_std:8082"; 
    private static final String BASE_URL1 = "http://192.168.240.119:8081"; 
    private static final String BASE_URL2 = "http://192.168.240.119:8082"; 

    public String buildServiceUrl(String path) {
        String baseUrl;

        if (path.startsWith("/dms")) {
            baseUrl = BASE_URL1;
            System.out.println("dmsdmsdsmdsmdsdmsmdsmds :" + path);
        } else if (path.startsWith("/std")) {
        	System.out.println("stdstdtsdtsdtsdtsdtstdtsdts :" + path);
            baseUrl = BASE_URL2;
        } else {
            baseUrl = BASE_URL1;
        }

        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(path) 
                .toUriString(); 
    }
}

