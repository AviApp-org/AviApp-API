package br.com.aviapp.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.usecases.collectEgg.CreateEggCollectUseCase;

@RestController
@RequestMapping("/api/collect-egg")
@CrossOrigin(origins = "*")
public class CollectEggController {
    
    private final CreateEggCollectUseCase createEggCollectUseCase;
    
    public CollectEggController(CreateEggCollectUseCase createEggCollectUseCase) {
        this.createEggCollectUseCase = createEggCollectUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectEggDataDTO> createCollectEgg(CollectEggDataDTO collectEggDataDTO) {
        CollectEggDataDTO collectEggData = createEggCollectUseCase.invoke(collectEggDataDTO);
        return ResponseEntity.ok(collectEggData);
    }
}
