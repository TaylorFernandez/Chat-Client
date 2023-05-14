package com.Taylor.ChatProject.datasource.communications;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {
    private HttpClient client;
    private static HTTPClient singleton;

    private HTTPClient(){
        client = HttpClient.newHttpClient();
    }

    public static HTTPClient getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new HTTPClient();
        return singleton;
    }

    public HttpRequest createHttpPostRequest(String url, String data){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        return request;
    }

    public HttpRequest createHttpGetRequest(String url){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return request;
    }

    public HttpResponse<String> getHttpResponse(HttpRequest request) throws IOException, InterruptedException {
        System.out.println("Sending Request");
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
