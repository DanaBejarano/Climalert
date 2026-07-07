package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.entities.WeatherRecord;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String[] DESTINATARIOS = {
            "dbejarano@frba.utn.edu.ar"

    };

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarAlerta(WeatherRecord record) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(DESTINATARIOS);
        message.setSubject("⚠️ ALERTA CLIMATICA - " + record.getLocation());
        message.setText(construirCuerpo(record));
        mailSender.send(message);
    }

    private String construirCuerpo(WeatherRecord r) {
        return """
                Se detecto una condicion climatica peligrosa/inusual en %s.

                Detalle completo del clima:
                - Temperatura: %.1f °C
                - Sensacion termica: %.1f °C
                - Humedad: %d %%
                - Viento: %.1f km/h
                - Condicion: %s
                - Ultima actualizacion del proveedor: %s
                - Registrado por Climalert el: %s

                Criterio de alerta activado: temperatura > 35°C y humedad > 60%%.

                Este es un mensaje automatico generado por Climalert.
                """.formatted(
                r.getLocation(),
                r.getTemperatureC(),
                r.getFeelsLikeC(),
                r.getHumidity(),
                r.getWindKph(),
                r.getConditionText(),
                r.getProviderLastUpdated(),
                r.getRetrievedAt()
        );
    }
}