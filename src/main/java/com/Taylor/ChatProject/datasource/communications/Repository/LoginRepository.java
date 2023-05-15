package com.Taylor.ChatProject.datasource.communications.Repository;

import com.Taylor.ChatProject.datasource.communications.Request.Request;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetLoginStatus;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.Taylor.ChatProject.datasource.communications.Response.Response;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetLoginStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class LoginRepository {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public LoginRepository(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = "http://localhost:8080/";
    }

    public BasicResponse validateLoginInformation(RequestGetLoginStatus request){
        String url = baseUrl + "/login";
        return performPostRequest(url, request, BasicResponse.class);
    }

    private <T> T performPostRequest(String url, Object request, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        System.out.println("entity.toString(): " + entity.toString());
        return restTemplate.postForObject(url, entity, responseType);
    }

    private <T> T performGetRequest(String url, Object request, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Convert request object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
    }
}
