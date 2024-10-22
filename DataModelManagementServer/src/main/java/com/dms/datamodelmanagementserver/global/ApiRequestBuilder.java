package com.dms.datamodelmanagementserver.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Scope("prototype")
@Slf4j
public class ApiRequestBuilder<T> {
    private String url;
    private Object request;
    private Class<T> responseType;
    private ParameterizedTypeReference<T> parameterizedTypeReference;
    private final HttpHeaders headers = new HttpHeaders();
    private HttpMethod method = HttpMethod.POST;

    public final UrlBuilder urlBuilder;
    private final RestTemplate restTemplate;

    public ApiRequestBuilder(UrlBuilder urlBuilder, RestTemplateBuilder restTemplateBuilder) {
        this.urlBuilder = urlBuilder;
        this.restTemplate = restTemplateBuilder.build();
    }


    public ApiRequestBuilder<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    public ApiRequestBuilder<T> setObject(Object request) {
        this.request = request;
        return this;
    }

    public ApiRequestBuilder<T> setResponseType(Class<T> responseType) {
        this.responseType = responseType;
        return this;
    }

    public ApiRequestBuilder<T> setResponseType(ParameterizedTypeReference<T> parameterizedTypeReference) {
        this.parameterizedTypeReference = parameterizedTypeReference;
        return this;
    }

    public ApiRequestBuilder<T> addHeader(String name, String value) {
    	if(headers != null) {
    		headers.remove(name);
    	}
        headers.add(name, value);
        return this;
    }

    public ApiRequestBuilder<T> setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }
    
    public T execute() {
        String serviceUrl = urlBuilder.buildServiceUrl(url);
        HttpEntity<Object> requestEntity = createRequestEntity();
       
        try {
            ResponseEntity<T> responseEntity = null;
            if (responseType != null) {
                responseEntity = restTemplate.
                        exchange(serviceUrl, method, requestEntity, responseType);
            } else if (parameterizedTypeReference != null) {
                responseEntity = restTemplate.exchange(serviceUrl, method, requestEntity, parameterizedTypeReference);
            }
            return responseEntity.getBody();
        } catch (HttpClientErrorException.Unauthorized e) {
            // Unauthorized 예외 처리 로직 추가 또는 래핑 예외 처리 추가
            return null;
        }
    }


    private HttpEntity<Object> createRequestEntity() {
        if (method == HttpMethod.GET || (method == HttpMethod.POST && request == null)) {
            return new HttpEntity<>(headers);
        } else {
            return new HttpEntity<>(request, headers);
        }
    }
}
