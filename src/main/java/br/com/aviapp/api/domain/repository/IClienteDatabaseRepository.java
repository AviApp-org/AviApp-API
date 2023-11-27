package br.com.aviapp.api.domain.repository;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;

public interface IClienteDatabaseRepository {
  public ClienteDTO save(ClienteBO bo);
}
