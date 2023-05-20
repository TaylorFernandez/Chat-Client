package com.Taylor.ChatProject.datasource.communications.Repository;

import com.Taylor.ChatProject.datasource.ClientInformation;
import com.Taylor.ChatProject.datasource.communications.Request.RequestCreateNewUser;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetLoginStatus;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Repository
public class LoginRepository {
    private static LoginRepository singleton;
    private final RestTemplate restTemplate;
    private final String baseUrl;

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private LoginRepository(){
        restTemplateBuilder = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10));
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = ClientInformation.getSingleton().getServerAddress();

    }

    public static LoginRepository getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new LoginRepository();
        return singleton;
    }

    public BasicResponse validateLoginInformation(RequestGetLoginStatus request){
        String url = baseUrl + "/login";
        return performPostRequest(url, request, BasicResponse.class);
    }

    public BasicResponse createNewUser(RequestCreateNewUser request){
        String url = baseUrl + "/login/newUser";
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
