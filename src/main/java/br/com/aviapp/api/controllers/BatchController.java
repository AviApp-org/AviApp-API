package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.usecases.batch.CreateBatchUseCase;
import br.com.aviapp.api.application.usecases.batch.FindBatchByIdUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/batches")
public class BatchController {

    private final CreateBatchUseCase createBatchUseCase;
    private final FindBatchByIdUseCase findBatchByIdUseCase;

    public BatchController(CreateBatchUseCase createBatchUseCase, FindBatchByIdUseCase findBatchByIdUseCase) {
        this.createBatchUseCase = createBatchUseCase;
        this.findBatchByIdUseCase = findBatchByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<BatchDTO> createBatch(@Valid @RequestBody BatchDTO batchDTO){
        BatchDTO newBatch = createBatchUseCase.invoke(batchDTO);
        URI location = URI.create("/api/addresses/" + newBatch.id());
        return ResponseEntity.created(location).body(newBatch);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<BatchDTO> getBatchById(@PathVariable Long id){
        Optional<BatchDTO> batchDTO = findBatchByIdUseCase.invoke(id);
        return batchDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
