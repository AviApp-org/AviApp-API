package br.com.aviapp.api.infra.jakarta.repository;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.domain.repository.IClienteDatabaseRepository;
import br.com.aviapp.api.infra.jakarta.mappers.JakartaClienteMapper;
import br.com.aviapp.api.infra.jakarta.models.JakartaCliente;
import br.com.aviapp.api.infra.jpa.repository.IJpaClienteRepository;

public class JakartaClienteRepository implements IClienteDatabaseRepository {

  @Autowired
  IJpaClienteRepository repository;

  @Override
  public ClienteBO save(ClienteBO bo) {
    JakartaCliente entity = JakartaClienteMapper.toEntity(bo);
    JakartaCliente repositoryResponse = repository.save(entity);

    return JakartaClienteMapper.toDomain(repositoryResponse);
  }
  
} 