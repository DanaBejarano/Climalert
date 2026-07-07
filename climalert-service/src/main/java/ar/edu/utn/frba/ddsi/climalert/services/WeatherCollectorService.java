package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.dto.WeatherApiResponse;
import ar.edu.utn.frba.ddsi.climalert.models.entities.WeatherRecord;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.WeatherRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherCollectorService {

    private static final Logger log = LoggerFactory.getLogger(WeatherCollectorService.class);

    private final WeatherApiClient weatherApiClient;
    private final WeatherRecordRepository repository;

    public WeatherCollectorService(WeatherApiClient weatherApiClient,
                                   WeatherRecordRepository repository) {
        this.weatherApiClient = weatherApiClient;
        this.repository = repository;
    }

    @Scheduled(fixedRate = 300_000) // 5 minutos
    public void fetchAndStoreWeather() {
        try {
            WeatherApiResponse response = weatherApiClient.getCurrentWeather();
            WeatherApiResponse.Current c = response.getCurrent();

            WeatherRecord record = new WeatherRecord(
                    response.getLocation().getName(),
                    c.getTemp_c(),
                    c.getHumidity(),
                    c.getFeelslike_c(),
                    c.getWind_kph(),
                    c.getCondition() != null ? c.getCondition().getText() : "N/A",
                    c.getLast_updated(),
                    LocalDateTime.now()
            );

            repository.save(record);
            log.info("Clima guardado: {} - {}C, {}% humedad", record.getLocation(),
                    record.getTemperatureC(), record.getHumidity());
        } catch (Exception e) {
            log.error("Error al obtener/guardar el clima: {}", e.getMessage(), e);
        }
    }
}