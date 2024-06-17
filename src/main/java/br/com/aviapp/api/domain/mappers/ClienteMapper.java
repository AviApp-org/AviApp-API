package br.com.aviapp.api.domain.mappers;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;

public class ClienteMapper {
  public static ClienteBO toBO(ClientDTO dto) {
    ClienteBO bo = new ClienteBO(dto.getId(), dto.getName(), dto.getEmail(), dto.getCpf(), dto.getTelefone());
    bo.setStatus(dto.getStatus());
    return bo;
  }

  public static ClientDTO toDTO(ClienteBO bo) {
    ClientDTO dto = new ClientDTO();

    dto.setId(bo.getId());
    dto.setEmail(bo.getEmail());
    dto.setCpf(bo.getCpf());
    dto.setName(bo.getName());
    dto.setStatus(bo.getStatus());
    dto.setTelefone(bo.getTelefone());
    
    return dto;
  }
}
