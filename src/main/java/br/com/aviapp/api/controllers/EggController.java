package br.com.aviapp.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.domain.dto.EggDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlEggEntity;
import br.com.aviapp.api.services.EggService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/eggs")
public class EggController {
    
    @Autowired
    private EggService eggService;

    @GetMapping
    public ResponseEntity<List<EggDTO>> getAllEggs() {
        List<EggDTO> eggs = eggService.listEggs();
        return ResponseEntity.ok(eggs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EggDTO> findById(@PathVariable Long id) {
        Optional<MySqlEggEntity> eggEntityOptional = eggService.getEggById(id);
        if (eggEntityOptional.isPresent()) {
            MySqlEggEntity eggEntity = eggEntityOptional.get();
            EggDTO eggDTO = eggService.toEggDTO(eggEntity);
            return ResponseEntity.ok(eggDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EggDTO> createEgg(@RequestBody EggDTO eggDTO) {
        Long createdEggId = eggService.save(eggDTO);
        URI location = URI.create("/api/eggs/" + createdEggId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EggDTO> updateEgg(@PathVariable Long id, @RequestBody EggDTO eggDTO) {
        EggDTO updatedEgg = eggService.updateEgg(id, eggDTO);
        return ResponseEntity.ok(updatedEgg);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEgg(@PathVariable Long id) {
        eggService.deleteEgg(id);
        return ResponseEntity.noContent().build();
    }
}
