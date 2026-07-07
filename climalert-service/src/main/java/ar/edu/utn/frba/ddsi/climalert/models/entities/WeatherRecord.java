package ar.edu.utn.frba.ddsi.climalert.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_record")
public class WeatherRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private double temperatureC;
    private int humidity;
    private double feelsLikeC;
    private double windKph;
    private String conditionText;
    private String providerLastUpdated;
    private LocalDateTime retrievedAt;

    public WeatherRecord() {
    }

    public WeatherRecord(String location, double temperatureC, int humidity,
                         double feelsLikeC, double windKph, String conditionText,
                         String providerLastUpdated, LocalDateTime retrievedAt) {
        this.location = location;
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.feelsLikeC = feelsLikeC;
        this.windKph = windKph;
        this.conditionText = conditionText;
        this.providerLastUpdated = providerLastUpdated;
        this.retrievedAt = retrievedAt;
    }

    public Long getId() { return id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getTemperatureC() { return temperatureC; }
    public void setTemperatureC(double temperatureC) { this.temperatureC = temperatureC; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public double getFeelsLikeC() { return feelsLikeC; }
    public void setFeelsLikeC(double feelsLikeC) { this.feelsLikeC = feelsLikeC; }

    public double getWindKph() { return windKph; }
    public void setWindKph(double windKph) { this.windKph = windKph; }

    public String getConditionText() { return conditionText; }
    public void setConditionText(String conditionText) { this.conditionText = conditionText; }

    public String getProviderLastUpdated() { return providerLastUpdated; }
    public void setProviderLastUpdated(String providerLastUpdated) { this.providerLastUpdated = providerLastUpdated; }

    public LocalDateTime getRetrievedAt() { return retrievedAt; }
    public void setRetrievedAt(LocalDateTime retrievedAt) { this.retrievedAt = retrievedAt; }
}