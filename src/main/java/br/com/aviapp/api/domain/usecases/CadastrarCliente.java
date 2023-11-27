package br.com.aviapp.api.domain.usecases;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.domain.mappers.ClienteMapper;
import br.com.aviapp.api.domain.repository.IClienteDatabaseRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CadastrarCliente {

  private IClienteDatabaseRepository repository;

  public ClienteDTO execute(ClienteDTO dto) {
    ClienteBO bo = ClienteMapper.toBO(dto);
    ClienteDTO response = repository.save(bo);
    return response;
  }
  
}