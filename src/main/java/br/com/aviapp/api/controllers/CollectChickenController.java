package br.com.aviapp.api.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.aviapp.api.application.usecases.collectChicken.*;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.CollectChickenDTO;

@RestController
@RequestMapping("/api/collect-chicken")
@CrossOrigin("*")
public class CollectChickenController {

    private final CreateChickenCollectUseCase createChickenCollectUseCase;
    private final ListAllChickenCollectUseCase listAllChickenCollectUseCase;
    private final ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase;
    private final GetChickenCollectByDateUseCase getChickenCollectByDateUseCase;
    private final DeleteChickenCollectUseCase deleteChickenCollectUseCase;
    private final ListChickenCollectsByDateAndAviaryUseCase listChickenCollectsByDateAndAviaryUseCase;

    public CollectChickenController(CreateChickenCollectUseCase createChickenCollectUseCase,
                                    ListAllChickenCollectUseCase listAllChickenCollectUseCase, ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase, GetChickenCollectByDateUseCase getChickenCollectByDateUseCase, DeleteChickenCollectUseCase deleteChickenCollectUseCase, ListChickenCollectsByDateAndAviaryUseCase listChickenCollectsByDateAndAviaryUseCase) {
        this.createChickenCollectUseCase = createChickenCollectUseCase;
        this.listAllChickenCollectUseCase = listAllChickenCollectUseCase;
        this.listChickenCollectsByAviaryUseCase = listChickenCollectsByAviaryUseCase;
        this.getChickenCollectByDateUseCase = getChickenCollectByDateUseCase;
        this.deleteChickenCollectUseCase = deleteChickenCollectUseCase;
        this.listChickenCollectsByDateAndAviaryUseCase = listChickenCollectsByDateAndAviaryUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectChickenDTO> createChickenCollect(@RequestBody CollectChickenDTO collectChickenDTO) throws BusinessRuleException {
        CollectChickenDTO chickenCollected = createChickenCollectUseCase.invoke(collectChickenDTO);
        URI location = URI.create("/api/chicken-collects/" + chickenCollected.id());
        return ResponseEntity.created(location).body(chickenCollected);
    }

    @GetMapping
    public ResponseEntity<List<CollectChickenDTO>> listAllChickenCollects() {
        List<CollectChickenDTO> chickenCollects = listAllChickenCollectUseCase.invoke();
        return ResponseEntity.ok(chickenCollects);
    }

    @GetMapping("/aviary/{id}")
    public ResponseEntity<List<CollectChickenDTO>> listChickenCollectsByAviary(@PathVariable Long id) {
        List<CollectChickenDTO> chickenCollects = listChickenCollectsByAviaryUseCase.invoke(id);
        return ResponseEntity.ok(chickenCollects);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CollectChickenDTO>> getChickenCollectByDate(
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        List<CollectChickenDTO> chickenCollects = getChickenCollectByDateUseCase.invoke(date);
        return ResponseEntity.ok(chickenCollects);
    }

    @GetMapping("/date/{date}/aviary/{aviaryId}")
    public ResponseEntity<List<CollectChickenDTO>> listChickenCollectsByDateAndAviary(
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
            @PathVariable Long aviaryId) {

        List<CollectChickenDTO> chickenCollects = listChickenCollectsByDateAndAviaryUseCase.invoke(aviaryId, date);
        return ResponseEntity.ok(chickenCollects);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChickenCollect(@PathVariable Long id) {
        deleteChickenCollectUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
