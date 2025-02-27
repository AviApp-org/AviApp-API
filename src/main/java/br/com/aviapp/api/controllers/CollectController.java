package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.usecases.collect.CreateCollectUseCase;
import br.com.aviapp.api.application.usecases.collect.DeleteCollectUseCase;
import br.com.aviapp.api.application.usecases.collect.ListAllCollectsUseCase;
import br.com.aviapp.api.application.usecases.collect.ListCollectsByAviaryUseCase;
import br.com.aviapp.api.application.usecases.collect.ListCollectsByEmployeeUseCase;

@RestController
@RequestMapping("/api/collects")
@CrossOrigin("*")
public class CollectController {

    private final CreateCollectUseCase createCollectUseCase;
    private final DeleteCollectUseCase deleteCollectUseCase;
    private final ListAllCollectsUseCase listAllCollectsUseCase;
    private final ListCollectsByAviaryUseCase listCollectsByAviaryUseCase;
    private final ListCollectsByEmployeeUseCase listCollectsByEmployeeUseCase;

    public CollectController(CreateCollectUseCase createCollectUseCase, DeleteCollectUseCase deleteCollectUseCase,
            ListAllCollectsUseCase listAllCollectsUseCase, ListCollectsByAviaryUseCase listCollectsByAviaryUseCase,
            ListCollectsByEmployeeUseCase listCollectsByEmployeeUseCase) {
        this.createCollectUseCase = createCollectUseCase;
        this.deleteCollectUseCase = deleteCollectUseCase;
        this.listAllCollectsUseCase = listAllCollectsUseCase;
        this.listCollectsByAviaryUseCase = listCollectsByAviaryUseCase;
        this.listCollectsByEmployeeUseCase = listCollectsByEmployeeUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectDTO> createCollect(CollectDTO collectDTO) {
        CollectDTO createdCollect = createCollectUseCase.invoke(collectDTO);
        URI location = URI.create("/api/collects/" + createdCollect.id());

        return ResponseEntity.created(location).body(createdCollect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollect(Long id) {
        deleteCollectUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CollectDTO>> listAllCollects() {
        List<CollectDTO> collects = listAllCollectsUseCase.invoke();
        return ResponseEntity.ok(collects);
    }

    @GetMapping("/aviary/{aviaryId}")
    public ResponseEntity<List<CollectDTO>> listCollectsByAviary(Long aviaryId) {
        List<CollectDTO> collects = listCollectsByAviaryUseCase.invoke(aviaryId);
        return ResponseEntity.ok(collects);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<CollectDTO>> listCollectsByEmployee(Long employeeId) {
        List<CollectDTO> collects = listCollectsByEmployeeUseCase.invoke(employeeId);
        return ResponseEntity.ok(collects);
    }
}
