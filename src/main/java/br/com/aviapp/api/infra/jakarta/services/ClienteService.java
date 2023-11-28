package br.com.aviapp.api.infra.jakarta.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.domain.repository.IClienteDatabaseRepository;
import br.com.aviapp.api.infra.jakarta.repository.JakartaClienteRepository;

public class ClienteService implements IClienteDatabaseRepository {
  @Autowired
  private JakartaClienteRepository repository;

  @Override
  public ClienteBO save(ClienteBO bo) {
    ClienteBO response = repository.save(bo);

    return response;
  }
  

}
