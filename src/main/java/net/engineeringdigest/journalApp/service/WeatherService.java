package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WheatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apikey="aae948932de475143f30232322839c45";

    private static final String API="https://api.weatherstack.com/current%20?%20access_key=API_kEY%20&%20query%20=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WheatherResponse getWeather(String city){
        String finalAPI=API.replace("CITY",city).replace("API_KEY",apikey);

        
        ResponseEntity<WheatherResponse> response= restTemplate.exchange(finalAPI, HttpMethod.POST, null, WheatherResponse.class);
        WheatherResponse body= response.getBody();
        return body;
    }


}
