package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.entities.WeatherRecord;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.WeatherRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertService {

    private static final Logger log = LoggerFactory.getLogger(AlertService.class);

    private static final double TEMP_LIMITE = 35.0;
    private static final int HUMEDAD_LIMITE = 60 ;

    private final WeatherRecordRepository repository;
    private final EmailService emailService;

    public AlertService(WeatherRecordRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60_000) // 1 minutito
    public void analizarUltimoClima() {
        Optional<WeatherRecord> ultimo = repository.findTopByOrderByRetrievedAtDesc();

        if (ultimo.isEmpty()) {
            log.info("Todavia no hay datos climaticos guardados, se esperara al proximo ciclo.");
            return;
        }

        WeatherRecord record = ultimo.get();

        if (esCondicionCritica(record)) {
            log.warn("¡Condicion critica detectada! temp={}C humedad={}% -> enviando alerta",
                    record.getTemperatureC(), record.getHumidity());
            emailService.enviarAlerta(record);
        } else {
            log.info("Clima dentro de parametros normales (temp={}C, humedad={}%)",
                    record.getTemperatureC(), record.getHumidity());
        }
    }

    private boolean esCondicionCritica(WeatherRecord record) {
        return record.getTemperatureC() > TEMP_LIMITE && record.getHumidity() > HUMEDAD_LIMITE;
    }
}