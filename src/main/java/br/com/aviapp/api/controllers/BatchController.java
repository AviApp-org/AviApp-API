package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.BatchDTO;
import br.com.aviapp.api.application.usecases.batch.*;
import br.com.aviapp.api.domain.errors.BusinessRuleException;
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
    private final ActivateBatchUseCase activateBatchUseCase;
    private final ListBatchesByFarmIdUseCase listBatchesByFarmIdUseCase;
    private final ListActiveBatchesByFarmUseCase listActiveBatchesByFarmUseCase;
    private final DeleteBatchUseCase deleteBatchUseCase;
    private final UpdateBatchUseCase updateBatchUseCase;

    public BatchController(CreateBatchUseCase createBatchUseCase, FindBatchByIdUseCase findBatchByIdUseCase,
                           DeactivateBatchUseCase deactivateBatchUseCase, ActivateBatchUseCase activateBatchUseCase, ListBatchesByFarmIdUseCase listBatchesByFarmIdUseCase, ListActiveBatchesByFarmUseCase listActiveBatchesByFarmUseCase, DeleteBatchUseCase deleteBatchUseCase, UpdateBatchUseCase updateBatchUseCase) {
        this.createBatchUseCase = createBatchUseCase;
        this.findBatchByIdUseCase = findBatchByIdUseCase;
        this.deactivateBatchUseCase = deactivateBatchUseCase;
        this.activateBatchUseCase = activateBatchUseCase;
        this.listBatchesByFarmIdUseCase = listBatchesByFarmIdUseCase;
        this.listActiveBatchesByFarmUseCase = listActiveBatchesByFarmUseCase;
        this.deleteBatchUseCase = deleteBatchUseCase;
        this.updateBatchUseCase = updateBatchUseCase;
    }

    @PostMapping
    public ResponseEntity<BatchDTO> createBatch(@Valid @RequestBody BatchDTO batchDTO) throws BusinessRuleException {
        BatchDTO newBatch = createBatchUseCase.invoke(batchDTO);
        URI location = URI.create("/api/addresses/" + newBatch.id());
        return ResponseEntity.created(location).body(newBatch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchDTO> getBatchById(@PathVariable Long id) {
        Optional<BatchDTO> batchDTO = findBatchByIdUseCase.invoke(id);
        return batchDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/active/farm/{farmId}")
    public ResponseEntity<List<BatchDTO>> getActiveBatches(@PathVariable Long farmId) {
        List<BatchDTO> batches = listActiveBatchesByFarmUseCase.invoke(farmId);
        return ResponseEntity.ok(batches);
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

    @PutMapping("/{id}")
    public ResponseEntity<BatchDTO> updateBatch(@PathVariable Long id, @Valid @RequestBody BatchDTO batchDTO) {
        Optional<BatchDTO> updatedBatch = updateBatchUseCase.invoke(id, batchDTO);
        return updatedBatch.map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        deleteBatchUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
