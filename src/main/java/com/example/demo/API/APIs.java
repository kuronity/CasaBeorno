package com.example.demo.API;

import com.google.gson.Gson;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class APIs {

    // .GET() is the standard method if no other is specified
    Gson gson = new Gson();

    public List<String> boredAPI() throws Exception {

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://www.boredapi.com/api/activity/"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        BoredAPI bored = gson.fromJson(getResponse.body(), BoredAPI.class);

        // The whole response in Json
        // String boredTest = gson.toJson(getResponse.body());
        // System.out.println(boredTest);


        String jsonRequest = gson.toJson(bored);
        // System.out.println("jsonRequest: " + jsonRequest);

        return List.of(bored.getActivity(), bored.getLink());
    }
    public String openDotaAPI(long id) throws Exception {
        // long id = 7342378199L;
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.opendota.com/api/matches/" + id))
                .build();

        System.out.println("dota GetRequest: " + getRequest);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("getResponse: " + getResponse);
        OpenDotaAPI openDotaAPI = gson.fromJson(getResponse.body(), OpenDotaAPI.class);
        //openDotaAPI.setId(7342378199L);

        String jsonRequest = gson.toJson(openDotaAPI);
        System.out.println("dota jsonRequest: " + jsonRequest);

        System.out.println("get picks and bans: " + openDotaAPI.getPicks_bans());
        System.out.println("Game duration " + openDotaAPI.getDuration());

        return openDotaAPI.getDuration();
    }
}
