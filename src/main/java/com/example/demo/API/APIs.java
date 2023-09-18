package com.example.demo.API;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

@Service
public class APIs {

    // .GET() is the standard method if no other is specified
    Gson gson = new Gson();

    public String boredAPI() throws Exception {
        BoredAPI bored;
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://www.boredapi.com/api/activity/"))
                .build();
        System.out.println("getRequest: " + getRequest);



        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        bored = gson.fromJson(getResponse.body(), BoredAPI.class);


        System.out.println("getResponse: " + getResponse);
        System.out.println("bored object: " + bored);
        System.out.println("bored specific activity: " + bored.getActivity());

        return bored.getActivity();
    }
}
