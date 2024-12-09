package br.com.aviapp.api.application.interfaces.controllers;

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

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAviaryEntity;
import br.com.aviapp.api.services.AviaryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/aviaries")
public class AviaryController {
   
    @Autowired
    private AviaryService aviaryService;

    @GetMapping
    public ResponseEntity<List<AviaryDTO>> getAllAviaries() {
        List<AviaryDTO> aviaries = aviaryService.listAviaries();
        return ResponseEntity.ok(aviaries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AviaryDTO> findById(@PathVariable Long id) {
        Optional<MySqlAviaryEntity> aviaryEntityOptional = aviaryService.getAviaryById(id);
        if (aviaryEntityOptional.isPresent()) {
            MySqlAviaryEntity aviaryEntity = aviaryEntityOptional.get();
            AviaryDTO aviaryDTO = aviaryService.toAviaryDTO(aviaryEntity);
            return ResponseEntity.ok(aviaryDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PostMapping
    public ResponseEntity<AviaryDTO> createAviary(@RequestBody AviaryDTO AviaryDTO) {
        Long createdAviaryId = aviaryService.save(AviaryDTO);
        URI location = URI.create("/api/aviaries/" + createdAviaryId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AviaryDTO> updateAviary(@PathVariable Long id, @RequestBody AviaryDTO AviaryDTO) {
        AviaryDTO updatedAviary = aviaryService.updateAviary(id, AviaryDTO);
        return ResponseEntity.ok(updatedAviary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAviary(@PathVariable Long id) {
        aviaryService.deleteAviary(id);
        return ResponseEntity.noContent().build();
    }*/
}
