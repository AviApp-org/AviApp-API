package br.com.aviapp.api.infra.jakarta.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.domain.repository.IClienteDatabaseRepository;
import br.com.aviapp.api.infra.jakarta.repository.JakartaClienteRepository;

public class ClienteService implements IClienteDatabaseRepository {
  @Autowired
  private JakartaClienteRepository repository;

  @Override
  public ClienteDTO save(ClienteBO bo) {
    repository.save(bo);
  }
  

}
