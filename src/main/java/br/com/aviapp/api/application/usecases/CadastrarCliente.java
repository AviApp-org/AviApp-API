package br.com.aviapp.api.application.usecases;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.application.mappers.ClienteMapper;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.infra.mysql.repository.IClienteDatabaseRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CadastrarCliente {

  private IClienteDatabaseRepository repository;

  public ClientDTO execute(ClientDTO dto) {
    ClientBO bo = ClienteMapper.toBO(dto);
    ClientBO repositoryResponse = repository.save(bo);
    ClientDTO response = ClienteMapper.toDTO(repositoryResponse);
    return response;
  }

}