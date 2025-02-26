package br.com.aviapp.api.controllers;

import br.com.aviapp.api.application.dto.EggValueDTO;
import br.com.aviapp.api.application.usecases.eggValue.CreateEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.DeleteEggValueUseCase;
import br.com.aviapp.api.application.usecases.eggValue.ListAllEggValuesUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/egg-value")
@CrossOrigin("*")
public class EggValueController {

    private final CreateEggValueUseCase createEggValueUseCase;
    private final ListAllEggValuesUseCase listAllEggValuesUseCase;
    private final DeleteEggValueUseCase deleteEggValueUseCase;

    public EggValueController(CreateEggValueUseCase createEggValueUseCase, ListAllEggValuesUseCase listAllEggValuesUseCase, DeleteEggValueUseCase deleteEggValueUseCase) {
        this.createEggValueUseCase = createEggValueUseCase;
        this.listAllEggValuesUseCase = listAllEggValuesUseCase;
        this.deleteEggValueUseCase = deleteEggValueUseCase;
    }

    @PostMapping
    public ResponseEntity<EggValueDTO> createEggValue(@Valid @RequestBody EggValueDTO eggValueDTO) {
        EggValueDTO newEggValue = createEggValueUseCase.invoke(eggValueDTO);
        URI location = URI.create("/api/egg-value/" + newEggValue.id());
        return ResponseEntity.created(location).body(newEggValue);
    }

    @GetMapping
    public ResponseEntity<List<EggValueDTO>> listAllEggValues() {
        List<EggValueDTO> eggValues = listAllEggValuesUseCase.invoke();
        return ResponseEntity.ok(eggValues);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEggValue(@PathVariable Long id) {
        deleteEggValueUseCase.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
