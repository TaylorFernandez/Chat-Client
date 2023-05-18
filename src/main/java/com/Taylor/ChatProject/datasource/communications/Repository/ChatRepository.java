package com.Taylor.ChatProject.datasource.communications.Repository;

import com.Taylor.ChatProject.datasource.communications.Request.RequestGetChats;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetPeers;
import com.Taylor.ChatProject.datasource.communications.Request.RequestSendNewChat;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetChats;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetPeers;
import com.Taylor.ChatProject.datasource.model.report.ChatForUsersReport;
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
public class ChatRepository {
    private static ChatRepository singleton;

    private final RestTemplate restTemplate;

    private final String baseUrl;

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private ChatRepository(){
        restTemplateBuilder = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10));
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = "http://localhost:8080/";
    }

    public static ChatRepository getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new ChatRepository();
        return singleton;
    }

    public ResponseGetChats getChatsForUsers(RequestGetChats request){
        String url = baseUrl + "/chat/getChatForUsers";
        return performPostRequest(url, request, ResponseGetChats.class);
    }

    public ResponseGetPeers getPeers(RequestGetPeers request){
        String url = baseUrl + "/chat/getPeers";
        return performPostRequest(url, request, ResponseGetPeers.class);
    }

    public BasicResponse sendNewChat(RequestSendNewChat request){
        String url = baseUrl + "/chat/newChat";
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
