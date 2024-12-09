package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClientBO;

public class ClienteMapper {
  public static ClientBO toBO(ClientDTO dto) {
    ClientBO bo = new ClientBO(dto.getId(), dto.getName(), dto.getEmail(), dto.getCpf(), dto.getTelefone());
    bo.setStatus(dto.getStatus());
    return bo;
  }

  public static ClientDTO toDTO(ClientBO bo) {
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
