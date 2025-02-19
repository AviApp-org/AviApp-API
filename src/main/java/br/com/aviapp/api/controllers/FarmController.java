package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import br.com.aviapp.api.application.usecases.farm.DeleteFarmByIdUseCase;
import br.com.aviapp.api.application.usecases.farm.ListAllFarmsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final ListAllFarmsUseCase listFarms;
    private final DeleteFarmByIdUseCase deleteFarm;

    public FarmController(CreateFarmUseCase createFarm, FindFarmByIdUseCase findFarm, ListAllFarmsUseCase listFarms, DeleteFarmByIdUseCase deleteFarm) {
        this.createFarm = createFarm;
        this.findFarm = findFarm;
        this.listFarms = listFarms;
        this.deleteFarm = deleteFarm;
    }

    @GetMapping
    public ResponseEntity<List<FarmDTO>> listAllFarms() {
        List<FarmDTO> farms = listFarms.invoke();
        return ResponseEntity.ok(farms);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        deleteFarm.invoke(id);
        return ResponseEntity.noContent().build();
    }
}
