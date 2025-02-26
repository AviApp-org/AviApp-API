package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.ChickenDTO;
import br.com.aviapp.api.application.usecases.chicken.createChickenUseCase;
import br.com.aviapp.api.application.usecases.chicken.listChickensUseCase;
import br.com.aviapp.api.application.usecases.chicken.updateChickenUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/chickens")
public class ChickenController {

    private final createChickenUseCase createChickenUseCase;
    private final updateChickenUseCase updateChickenUseCase;
    private final listChickensUseCase listChickensUseCase;

    public ChickenController(createChickenUseCase createChickenUseCase,
            updateChickenUseCase updateChickenUseCase,
            listChickensUseCase listChickensUseCase) {
        this.createChickenUseCase = createChickenUseCase;
        this.updateChickenUseCase = updateChickenUseCase;
        this.listChickensUseCase = listChickensUseCase;
    }

    @PostMapping
    public ResponseEntity<ChickenDTO> createChicken(@Valid @RequestBody ChickenDTO chickenDTO) {
        ChickenDTO newChicken = createChickenUseCase.invoke(chickenDTO);
        URI location = URI.create("/api/chickens/" + newChicken.id());
        return ResponseEntity.created(location).body(newChicken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChickenDTO> updateChicken(@PathVariable Long id, @Valid @RequestBody ChickenDTO chickenDTO) {
        Optional<ChickenDTO> updatedChicken = updateChickenUseCase.invoke(chickenDTO, id);
        return updatedChicken.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ChickenDTO>> listChickens() {
        List<ChickenDTO> chickens = listChickensUseCase.invoke();
        return ResponseEntity.ok(chickens);
    }
}
