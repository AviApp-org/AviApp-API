package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.usecases.batch.*;
import jakarta.validation.Valid;

import org.aspectj.weaver.BCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/batches")
public class BatchController {

    private final CreateBatchUseCase createBatchUseCase;
    private final FindBatchByIdUseCase findBatchByIdUseCase;
    private final DeactivateBatchUseCase deactivateBatchUseCase;
    private final ActivateBatchUseCase  activateBatchUseCase;   
    private final ListBatchesByFarmIdUseCase listBatchesByFarmIdUseCase;
    private final DeleteBatchUseCase deleteBatchUseCase;

    public BatchController(CreateBatchUseCase createBatchUseCase, FindBatchByIdUseCase findBatchByIdUseCase,
                           DeactivateBatchUseCase deactivateBatchUseCase, ActivateBatchUseCase activateBatchUseCase, ListBatchesByFarmIdUseCase listBatchesByFarmIdUseCase, DeleteBatchUseCase deleteBatchUseCase) {
        this.createBatchUseCase = createBatchUseCase;
        this.findBatchByIdUseCase = findBatchByIdUseCase;
        this.deactivateBatchUseCase = deactivateBatchUseCase;
        this.activateBatchUseCase = activateBatchUseCase;
        this.listBatchesByFarmIdUseCase = listBatchesByFarmIdUseCase;
        this.deleteBatchUseCase = deleteBatchUseCase;
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

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<BatchDTO>> getBatchesByFarmId(@PathVariable Long farmId) {
        List<BatchDTO> batches = listBatchesByFarmIdUseCase.invoke(farmId);
        return ResponseEntity.ok(batches);
    }

    @PatchMapping("/{batchId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateClient(@PathVariable Long batchId) {
        try {
            activateBatchUseCase.invoke(batchId);
        } catch (BCException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/{batchId}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateBatch(@PathVariable Long batchId) {
        try {
            deactivateBatchUseCase.invoke(batchId);
        } catch (BCException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        deleteBatchUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
