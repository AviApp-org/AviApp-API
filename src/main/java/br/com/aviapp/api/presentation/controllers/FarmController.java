package br.com.aviapp.api.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.domain.dto.FarmDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.FarmRepository;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/farm")
public class FarmController {

  FarmRepository repository;

  public FarmController(FarmRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public ResponseEntity<List<MySqlFarmEntity>> getAll() {
    List<MySqlFarmEntity> entities = repository.findAll();
    return ResponseEntity.ok(entities);
  }

  @PostMapping
  public ResponseEntity<List<MySqlFarmEntity>> create(@RequestBody FarmDTO farmDTO) {
    if (farmDTO.getAddress() == null) {
      throw new RuntimeException("Campo obrigatório vazio: endereço");
    }

    if (farmDTO.getClientId() == null) {
      throw new RuntimeException("Campo obrigatório vazio: cliente");
    }

    MySqlAddressEntity addressEntity = new MySqlAddressEntity(null, )

    MySqlFarmEntity entity = new MySqlFarmEntity(null, farmDTO.getName(), );

    return ResponseEntity.ok(entities);
  }

}
