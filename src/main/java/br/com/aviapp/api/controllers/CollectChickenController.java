package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;

import br.com.aviapp.api.application.usecases.collectChicken.ListChickenCollectsByAviaryUseCase;
import br.com.aviapp.api.application.usecases.collectChicken.ListChickenCollectsByEmployeeUseCase;
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
    private final ListChickenCollectsByEmployeeUseCase listChickenCollectsByEmployeeUseCase;
    private final ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase;

    public CollectChickenController(CreateChickenCollectUseCase createChickenCollectUseCase,
                                    ListAllChickenCollectUseCase listAllChickenCollectUseCase, ListChickenCollectsByEmployeeUseCase listChickenCollectsByEmployeeUseCase, ListChickenCollectsByAviaryUseCase listChickenCollectsByAviaryUseCase) {
        this.createChickenCollectUseCase = createChickenCollectUseCase;
        this.listAllChickenCollectUseCase = listAllChickenCollectUseCase;
        this.listChickenCollectsByEmployeeUseCase = listChickenCollectsByEmployeeUseCase;
        this.listChickenCollectsByAviaryUseCase = listChickenCollectsByAviaryUseCase;
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

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<CollectChickenDTO>> listChickenCollectsByEmployee(@PathVariable Long id) {
        List<CollectChickenDTO> chickenCollects = listChickenCollectsByEmployeeUseCase.invoke(id);
        return ResponseEntity.ok(chickenCollects);
    }

    @GetMapping("/aviary/{id}")
    public ResponseEntity<List<CollectChickenDTO>> listChickenCollectsByAviary(@PathVariable Long id) {
        List<CollectChickenDTO> chickenCollects = listChickenCollectsByAviaryUseCase.invoke(id);
        return ResponseEntity.ok(chickenCollects);
    }

}
