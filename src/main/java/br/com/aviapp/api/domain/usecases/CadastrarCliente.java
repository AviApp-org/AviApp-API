package br.com.aviapp.api.domain.usecases;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.domain.mappers.ClienteMapper;
import br.com.aviapp.api.infra.mysql.repository.IClienteDatabaseRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CadastrarCliente {

  private IClienteDatabaseRepository repository;

  public ClientDTO execute(ClientDTO dto) {
    ClienteBO bo = ClienteMapper.toBO(dto);
    ClienteBO repositoryResponse = repository.save(bo);
    ClientDTO response = ClienteMapper.toDTO(repositoryResponse);
    return response;
  }

}