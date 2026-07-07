package ar.edu.utn.frba.ddsi.climalert.models.repositories;

import ar.edu.utn.frba.ddsi.climalert.models.entities.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {
    Optional<WeatherRecord> findTopByOrderByRetrievedAtDesc();
}