package br.com.aviapp.api.controllers;

import java.net.URI;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aviapp.api.application.dto.CollectDTO;
import br.com.aviapp.api.application.usecases.collect.CreateCollectUseCase;
import br.com.aviapp.api.application.usecases.collect.DeleteCollectUseCase;

@RestController
@RequestMapping("/api/collects")
@CrossOrigin("*")
public class CollectController {

    private final CreateCollectUseCase createCollectUseCase;
    private final DeleteCollectUseCase deleteCollectUseCase;


    public CollectController(CreateCollectUseCase createCollectUseCase, DeleteCollectUseCase deleteCollectUseCase
  ) {
        this.createCollectUseCase = createCollectUseCase;
        this.deleteCollectUseCase = deleteCollectUseCase;

    }

    @PostMapping
    public ResponseEntity<CollectDTO> createCollect(@Valid  @RequestBody CollectDTO collectDTO) {
        CollectDTO createdCollect = createCollectUseCase.invoke(collectDTO);
        URI location = URI.create("/api/collects/" + createdCollect.id());

        return ResponseEntity.created(location).body(createdCollect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollect(@PathVariable Long id) {
        deleteCollectUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }


}
