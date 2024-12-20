package br.com.aviapp.api.infra.mysql.repository;

import br.com.aviapp.api.domain.entities.ClientBO;

public interface IClienteDatabaseRepositoryJPA {
  public ClientBO save(ClientBO bo);
}
