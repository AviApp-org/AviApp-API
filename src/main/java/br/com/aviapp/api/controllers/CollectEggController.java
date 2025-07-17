package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.usecases.collectEgg.*;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
import br.com.aviapp.api.infra.services.webSocket.CollectEggNotifier;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/collect-egg")
@CrossOrigin("*")
public class CollectEggController {

    private final CreateEggCollectUseCase createEggCollectUseCase;
    private final ListAllEggCollectsUseCase listAllEggCollectsUseCase;
    private final ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase;
    private final GetEggCollectByDateUseCase getEggCollectByDateUseCase;
    private final DeleteEggCollectUseCase deleteEggCollectUseCase;
    private final ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase;

    @Autowired
    CollectEggNotifier collectEggNotifier;

    public CollectEggController(CreateEggCollectUseCase createEggCollectUseCase, ListAllEggCollectsUseCase listAllEggCollectsUseCase, ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase, GetEggCollectByDateUseCase getEggCollectByDateUseCase, DeleteEggCollectUseCase deleteEggCollectUseCase, ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase) {
        this.createEggCollectUseCase = createEggCollectUseCase;
        this.listAllEggCollectsUseCase = listAllEggCollectsUseCase;
        this.listEggCollectsByAviaryUseCase = listEggCollectsByAviaryUseCase;
        this.getEggCollectByDateUseCase = getEggCollectByDateUseCase;
        this.deleteEggCollectUseCase = deleteEggCollectUseCase;
        this.listEggCollectsByDateAndAviaryUseCase = listEggCollectsByDateAndAviaryUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectEggDataDTO> createCollectEgg(@RequestBody CollectEggDataDTO collectEggDataDTO) throws BusinessRuleException {
        CollectEggDataDTO collectEggData = createEggCollectUseCase.invoke(collectEggDataDTO);
        collectEggNotifier.notifyNewCollect(collectEggData);

        return ResponseEntity.ok(collectEggData);
    }

    @GetMapping
    public ResponseEntity<List<CollectEggDataDTO>> listAllEggCollects() {
        List<CollectEggDataDTO> collectEggData = listAllEggCollectsUseCase.invoke();
        return ResponseEntity.ok(collectEggData);
    }

    @GetMapping("/aviary/{id}")
    public ResponseEntity<List<CollectEggDataDTO>> listByAviary(@PathVariable Long id) {
        List<CollectEggDataDTO> collectEggData = listEggCollectsByAviaryUseCase.invoke(id);
        return ResponseEntity.ok(collectEggData);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CollectEggDataDTO>> getEggCollectByDate(
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        List<CollectEggDataDTO> eggCollects = getEggCollectByDateUseCase.invoke(date);
        return ResponseEntity.ok(eggCollects);
    }

    @GetMapping("/date/{date}/aviary/{aviaryId}")
    public ResponseEntity<List<CollectEggDataDTO>> getEggCollectByDateAndAviary(
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
            @PathVariable Long aviaryId) {

        List<CollectEggDataDTO> eggCollects = listEggCollectsByDateAndAviaryUseCase.invoke(aviaryId, date);
        return ResponseEntity.ok(eggCollects);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectEggData(@PathVariable Long id) {
        deleteEggCollectUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
