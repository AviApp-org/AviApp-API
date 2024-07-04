package br.com.aviapp.api.domain.mappers;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClienteBO;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;

public class ClienteMapper {
  public static ClienteBO toBO(ClientDTO dto) {
    ClienteBO bo = new ClienteBO(dto.getId(), dto.getName(), dto.getEmail(), dto.getCpf(), dto.getPhone());
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
    dto.setPhone(bo.getPhone());

    return dto;
  }

  public static ClientDTO toDTO(MySqlClientEntity entity) {
    ClientDTO dto = new ClientDTO();

    dto.setId(entity.getId());
    dto.setEmail(entity.getEmail());
    dto.setCpf(entity.getCpf());
    dto.setName(entity.getName());
    dto.setStatus(entity.getStatus());
    dto.setPhone(entity.getPhone());

    return dto;
  }
}
