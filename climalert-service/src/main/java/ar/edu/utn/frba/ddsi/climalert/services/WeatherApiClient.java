package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.dto.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherApiClient {

    private final RestTemplate restTemplate;

    @Value("${weatherapi.base-url}")
    private String baseUrl;

    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.location}")
    private String location;

    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherApiResponse getCurrentWeather() {
        String url = UriComponentsBuilder.fromUriString(baseUrl + "/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .queryParam("aqi", "no")
                .toUriString();

        return restTemplate.getForObject(url, WeatherApiResponse.class);
    }
}