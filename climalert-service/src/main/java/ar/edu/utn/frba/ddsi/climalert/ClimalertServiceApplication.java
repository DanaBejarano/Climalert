package ar.edu.utn.frba.ddsi.climalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnableScheduling le dice a Spring que preste atencion a los metodos
 * marcados con @Scheduled en nuestros services (los jobs que corren
 * cada 5 minutos y cada 1 minuto).
 */
@SpringBootApplication
@EnableScheduling
public class ClimalertServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClimalertServiceApplication.class, args);
    }
}
