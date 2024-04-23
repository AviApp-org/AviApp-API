package br.com.aviapp.api.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aviapp.api.application.services.ClienteService;
import br.com.aviapp.api.domain.entities.ClienteBO;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
  
  @Autowired
  private final ClienteService service;

  public ClienteBO create(AddClienteDTO dto) {
    
  }

}
