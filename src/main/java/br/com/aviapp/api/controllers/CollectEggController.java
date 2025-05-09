package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.usecases.collectEgg.GetEggCollectByDateUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListAllEggCollectsUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByAviaryUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.usecases.collectEgg.CreateEggCollectUseCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/collect-egg")
@CrossOrigin(origins = "*")
public class CollectEggController {

    private final CreateEggCollectUseCase createEggCollectUseCase;
    private final ListAllEggCollectsUseCase listAllEggCollectsUseCase;
    private final ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase;
    private final GetEggCollectByDateUseCase getEggCollectByDateUseCase;

    public CollectEggController(CreateEggCollectUseCase createEggCollectUseCase, ListAllEggCollectsUseCase listAllEggCollectsUseCase, ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase, GetEggCollectByDateUseCase getEggCollectByDateUseCase) {
        this.createEggCollectUseCase = createEggCollectUseCase;
        this.listAllEggCollectsUseCase = listAllEggCollectsUseCase;
        this.listEggCollectsByAviaryUseCase = listEggCollectsByAviaryUseCase;
        this.getEggCollectByDateUseCase = getEggCollectByDateUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectEggDataDTO> createCollectEgg(@RequestBody CollectEggDataDTO collectEggDataDTO) {
        CollectEggDataDTO collectEggData = createEggCollectUseCase.invoke(collectEggDataDTO);
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
    public ResponseEntity<List<CollectEggDataDTO>> getChickenCollectByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // Convertendo LocalDate para LocalDateTime (início do dia)
        LocalDateTime dateTime = date.atStartOfDay();
        List<CollectEggDataDTO> chickenCollects = getEggCollectByDateUseCase.invoke(dateTime);
        return ResponseEntity.ok(chickenCollects);
    }

}
