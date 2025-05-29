package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.usecases.aviary.*;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.domain.errors.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.AviaryDTO;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/aviaries")
public class AviaryController {

    private final CreateAviaryUseCase createAviaryUseCase;
    private final ListAviariesByBatchUseCase listAviariesByBatchUseCase;
    private final FindAviaryByIdUseCase findAviaryByIdUseCase;
    private final DeleteAviaryUseCase deleteAviaryUseCase;
    private final ListAllAviariesUseCase listAllAviariesUseCase;
    private final UpdateAviaryUseCase updateAviaryUseCase;

    public AviaryController(CreateAviaryUseCase createAviaryUseCase,
                            ListAviariesByBatchUseCase listAviariesByBatchUseCase, FindAviaryByIdUseCase findAviaryByIdUseCase,
                            DeleteAviaryUseCase deleteAviaryUseCase, ListAllAviariesUseCase listAllAviariesUseCase, UpdateAviaryUseCase updateAviaryUseCase) {
        this.createAviaryUseCase = createAviaryUseCase;
        this.listAviariesByBatchUseCase = listAviariesByBatchUseCase;
        this.findAviaryByIdUseCase = findAviaryByIdUseCase;
        this.deleteAviaryUseCase = deleteAviaryUseCase;
        this.listAllAviariesUseCase = listAllAviariesUseCase;
        this.updateAviaryUseCase = updateAviaryUseCase;
    }

    @PostMapping
    public ResponseEntity<AviaryDTO> createAviary(@Valid @RequestBody AviaryDTO aviaryDTO) throws BusinessRuleException {
        AviaryDTO newAviary = createAviaryUseCase.invoke(aviaryDTO);
        URI location = URI.create("/api/aviaries/" + newAviary.id());
        return ResponseEntity.created(location).body(newAviary);
    }

    @GetMapping
    public ResponseEntity<List<AviaryDTO>> listAllAviarys() {
        List<AviaryDTO> aviaries = listAllAviariesUseCase.invoke();
        return ResponseEntity.ok(aviaries);
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

    @PutMapping("/{id}")
    public ResponseEntity<AviaryDTO> updateAviary(@PathVariable Long id, @Valid @RequestBody AviaryDTO aviaryDTO) {
        Optional<AviaryDTO> updatedAviary = updateAviaryUseCase.invoke(id, aviaryDTO);
        return updatedAviary.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAviary(@PathVariable Long id) throws BusinessRuleException, ResourceNotFoundException {
        deleteAviaryUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }

}
