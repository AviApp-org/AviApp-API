package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.dto.FarmDTO;
import br.com.aviapp.api.application.usecases.farm.CreateFarmUseCase;
import br.com.aviapp.api.application.usecases.farm.FindFarmByIdUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/farms")
public class FarmController {

    private final CreateFarmUseCase createFarm;
    private final FindFarmByIdUseCase findFarm;

    public FarmController(CreateFarmUseCase createFarm, FindFarmByIdUseCase findFarm) {
        this.createFarm = createFarm;
        this.findFarm = findFarm;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id) {
        Optional<FarmDTO> farm = findFarm.invoke(id);
        return farm.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FarmDTO> createFarm(@Valid @RequestBody FarmDTO farmDTO) {
        FarmDTO newFarm = createFarm.invoke(farmDTO);
        URI location = URI.create("/api/farms/" + newFarm.id());
        return ResponseEntity.created(location).body(newFarm);
    }
}
