package br.com.aviapp.api.domain.mappers;

import br.com.aviapp.api.domain.dto.ClienteDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;

public class ClienteMapper {
  public static ClienteBO toBO(ClienteDTO dto) {
    ClienteBO bo = new ClienteBO();

    bo.setId(dto.getId());
    bo.setEmail(dto.getEmail());
    bo.setCnpj(dto.getCnpj());
    bo.setNome(dto.getNome());
    bo.setStatus(dto.getStatus());
    bo.setTelefone(dto.getTelefone());
    
    return bo;
  }

  public static ClienteDTO toDTO(ClienteBO bo) {
    ClienteDTO dto = new ClienteDTO();

    dto.setId(bo.getId());
    dto.setEmail(bo.getEmail());
    dto.setCnpj(bo.getCnpj());
    dto.setNome(bo.getNome());
    dto.setStatus(bo.getStatus());
    dto.setTelefone(bo.getTelefone());
    
    return dto;
  }
}
