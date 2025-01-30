package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.AnomalyDTO;
import br.com.aviapp.api.application.usecases.anomaly.CreateAnomalyUseCase;
import br.com.aviapp.api.application.usecases.anomaly.DeleteAnomalyUseCase;
import br.com.aviapp.api.application.usecases.anomaly.ListAllAnomaliesUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/anomalies")
@CrossOrigin("*")
public class AnomalyController {

    private final CreateAnomalyUseCase createAnomalyUseCase;
    private final DeleteAnomalyUseCase deleteAnomalyUseCase;
    private final ListAllAnomaliesUseCase listAllAnomaliesUseCase;

    public AnomalyController(CreateAnomalyUseCase createAnomalyUseCase, DeleteAnomalyUseCase deleteAnomalyUseCase,
            ListAllAnomaliesUseCase listAllAnomaliesUseCase
 ) {
        this.createAnomalyUseCase = createAnomalyUseCase;
        this.deleteAnomalyUseCase = deleteAnomalyUseCase;
        this.listAllAnomaliesUseCase = listAllAnomaliesUseCase;
    }

    @PostMapping
    public ResponseEntity<AnomalyDTO> createAnomaly(@Valid @RequestBody AnomalyDTO anomalyDTO) {
        AnomalyDTO newAnomaly = createAnomalyUseCase.invoke(anomalyDTO);
        URI location = URI.create("/api/anomalies/" + newAnomaly.id());
        return ResponseEntity.created(location).body(newAnomaly);
    }



    @GetMapping
    public ResponseEntity<List<AnomalyDTO>> listAllAnomalies() {
        List<AnomalyDTO> anomalies = listAllAnomaliesUseCase.invoke();
        return ResponseEntity.ok(anomalies);
    }

    @DeleteMapping("/{anomalyId}")
    public ResponseEntity<Void> deleteAnomaly(@PathVariable Long anomalyId) {
        deleteAnomalyUseCase.invoke(anomalyId);
        return ResponseEntity.noContent().build();
    }
}
