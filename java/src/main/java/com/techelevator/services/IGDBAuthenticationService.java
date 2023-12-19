package com.techelevator.services;

import com.techelevator.model.IGDBModels.IGDBAuthToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IGDBAuthenticationService {

    private final String baseUrl = "https://id.twitch.tv/oauth2/token?" +
            "client_id=fp9g0g4yqrpfwoijr9iimtenbzigld&" +
            "client_secret=o1gjjylmv8srsdy3z1mftu92hz32wx&" +
            "grant_type=client_credentials";

    private final RestTemplate restTemplate = new RestTemplate();

    public IGDBAuthToken getToken() {
        return restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(new HttpHeaders()), IGDBAuthToken.class).getBody();
    }

}
