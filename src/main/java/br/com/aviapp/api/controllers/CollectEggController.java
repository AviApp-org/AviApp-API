package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.usecases.collectEgg.ListAllEggCollectsUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByAviaryUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByEmployeeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import br.com.aviapp.api.application.usecases.collectEgg.CreateEggCollectUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/collect-egg")
@CrossOrigin(origins = "*")
public class CollectEggController {
    
    private final CreateEggCollectUseCase createEggCollectUseCase;
    private final ListAllEggCollectsUseCase listAllEggCollectsUseCase;
    private final ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase;
    private final ListEggCollectsByEmployeeUseCase listEggCollectsByEmployeeUseCase;

    public CollectEggController(CreateEggCollectUseCase createEggCollectUseCase, ListAllEggCollectsUseCase listAllEggCollectsUseCase, ListEggCollectsByAviaryUseCase listEggCollectsByAviaryUseCase,
                                ListEggCollectsByEmployeeUseCase listEggCollectsByEmployeeUseCase) {
        this.createEggCollectUseCase = createEggCollectUseCase;
        this.listAllEggCollectsUseCase = listAllEggCollectsUseCase;
        this.listEggCollectsByAviaryUseCase = listEggCollectsByAviaryUseCase;
        this.listEggCollectsByEmployeeUseCase = listEggCollectsByEmployeeUseCase;
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

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<CollectEggDataDTO>> listByEmployee(@PathVariable Long id) {
        List<CollectEggDataDTO> collectEggData = listEggCollectsByEmployeeUseCase.invoke(id);
        return ResponseEntity.ok(collectEggData);
    }

    @GetMapping("/aviary/{id}")
    public ResponseEntity<List<CollectEggDataDTO>> listByAviary(@PathVariable Long id) {
        List<CollectEggDataDTO> collectEggData = listEggCollectsByAviaryUseCase.invoke(id);
        return ResponseEntity.ok(collectEggData);
    }
}
