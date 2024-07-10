package br.com.aviapp.api.infra.mysql.repository;

import br.com.aviapp.api.domain.entities.ClientBO;

public interface IClienteDatabaseRepository {
  public ClientBO save(ClientBO bo);
}
