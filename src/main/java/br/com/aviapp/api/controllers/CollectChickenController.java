package br.com.aviapp.api.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.aviapp.api.application.usecases.collectChicken.GetChickenCollectByDateUseCase;
import br.com.aviapp.api.application.usecases.collectChicken.ListChickenCollectsByAviaryUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.usecases.collectChicken.CreateChickenCollectUseCase;
import br.com.aviapp.api.application.usecases.collectChicken.ListAllChickenCollectUseCase;

@RestController
@RequestMapping("/api/collect-chicken")
@CrossOrigin("*")
public class CollectChickenController {

    private final CreateChickenCollectUseCase createChickenCollectUseCase;
    private final ListAllChickenCollectUseCase listAllChickenCollectUseCase;
    private final ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase;
    private final GetChickenCollectByDateUseCase getChickenCollectByDateUseCase;

    public CollectChickenController(CreateChickenCollectUseCase createChickenCollectUseCase,
                                    ListAllChickenCollectUseCase listAllChickenCollectUseCase, ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase, GetChickenCollectByDateUseCase getChickenCollectByDateUseCase) {
        this.createChickenCollectUseCase = createChickenCollectUseCase;
        this.listAllChickenCollectUseCase = listAllChickenCollectUseCase;
        this.listChickenCollectsByAviaryUseCase = listChickenCollectsByAviaryUseCase;
        this.getChickenCollectByDateUseCase = getChickenCollectByDateUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectChickenDTO> createChickenCollect(@RequestBody CollectChickenDTO collectChickenDTO) {
        CollectChickenDTO chickenCollected = createChickenCollectUseCase.invoken(collectChickenDTO);
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
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // Convertendo LocalDate para LocalDateTime (início do dia)
        LocalDateTime dateTime = date.atStartOfDay();
        List<CollectChickenDTO> chickenCollects = getChickenCollectByDateUseCase.invoke(dateTime);
        return ResponseEntity.ok(chickenCollects);
    }

}
