package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.CollectChickenDTO;
import br.com.aviapp.api.application.usecases.collectChicken.CreateChickenCollectUseCase;
import br.com.aviapp.api.application.usecases.collectChicken.ListAllChickenCollectUseCase;

@RestController
@RequestMapping("/api/collect-chicken")
@CrossOrigin("*")
public class CollectChickenController {

    private final CreateChickenCollectUseCase createChickenCollectUseCase;
    private final ListAllChickenCollectUseCase listAllChickenCollectUseCase;

    public CollectChickenController(CreateChickenCollectUseCase createChickenCollectUseCase,
            ListAllChickenCollectUseCase listAllChickenCollectUseCase) {
        this.createChickenCollectUseCase = createChickenCollectUseCase;
        this.listAllChickenCollectUseCase = listAllChickenCollectUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectChickenDTO> createChickenCollect(CollectChickenDTO collectChickenDTO) {
        CollectChickenDTO chickenCollected = createChickenCollectUseCase.invoken(collectChickenDTO);
        URI location = URI.create("/api/chicken-collects/" + chickenCollected.id());
        return ResponseEntity.created(location).body(chickenCollected);
    }

    @GetMapping
    public ResponseEntity<List<CollectChickenDTO>> listAllChickenCollects() {
        List<CollectChickenDTO> chickenCollects = listAllChickenCollectUseCase.invoke();
        return ResponseEntity.ok(chickenCollects);
    }
}
