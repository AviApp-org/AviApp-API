package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.usecases.aviary.CreateAviaryUseCase;
import br.com.aviapp.api.application.usecases.aviary.DeleteAviaryUseCase;
import br.com.aviapp.api.application.usecases.aviary.FindAviaryByIdUseCase;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/aviaries")
public class AviaryController {

    private final CreateAviaryUseCase createAviaryUseCase;
    private final ListAviariesByBatchUseCase listAviariesByBatchUseCase;
    private final FindAviaryByIdUseCase findAviaryByIdUseCase;
    private final DeleteAviaryUseCase deleteAviaryUseCase;

    public AviaryController(CreateAviaryUseCase createAviaryUseCase,
            ListAviariesByBatchUseCase listAviariesByBatchUseCase, FindAviaryByIdUseCase findAviaryByIdUseCase,
            DeleteAviaryUseCase deleteAviaryUseCase) {
        this.createAviaryUseCase = createAviaryUseCase;
        this.listAviariesByBatchUseCase = listAviariesByBatchUseCase;
        this.findAviaryByIdUseCase = findAviaryByIdUseCase;
        this.deleteAviaryUseCase = deleteAviaryUseCase;
    }

    @PostMapping
    public ResponseEntity<AviaryDTO> createAviary(@Valid @RequestBody AviaryDTO aviaryDTO) {
        AviaryDTO newAviary = createAviaryUseCase.invoke(aviaryDTO);
        URI location = URI.create("/api/aviaries/" + newAviary.id());
        return ResponseEntity.created(location).body(newAviary);
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<AviaryDTO>> listAviariesByBatch(@PathVariable Long batchId) {
        Optional<List<AviaryDTO>> aviaries = listAviariesByBatchUseCase.invoke(batchId);
        return aviaries.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AviaryDTO> findAviaryById(@PathVariable Long id) {
        Optional<AviaryDTO> aviary = findAviaryByIdUseCase.invoke(id);
        return aviary.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAviary(@PathVariable Long id) {
        deleteAviaryUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
