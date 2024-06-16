package br.com.aviapp.api.infra.mysql.repository;

import br.com.aviapp.api.domain.entities.ClienteBO;

public interface IClienteDatabaseRepository {
  public ClienteBO save(ClienteBO bo);
}
