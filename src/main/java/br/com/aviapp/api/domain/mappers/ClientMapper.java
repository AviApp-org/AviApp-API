package br.com.aviapp.api.domain.mappers;

import br.com.aviapp.api.domain.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClientBO;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.postgresql.entities.PgSqlClientEntity;
import br.com.aviapp.api.presentation.dto.request.client.CreateClientRequestDTO;
import br.com.aviapp.api.presentation.dto.response.client.CreateClientResponseDTO;

public class ClientMapper {
  public static ClientBO toBO(ClientDTO dto) {
    ClientBO bo = new ClientBO(dto.getId(), dto.getName(), dto.getEmail(), dto.getCpf(), dto.getPhone(), dto.getBirthDate());
    bo.setStatus(dto.getStatus());
    return bo;
  }

  public static ClientBO toBO(CreateClientRequestDTO dto) {
    ClientBO bo = new ClientBO(dto.getId(), dto.getName(), dto.getEmail(), dto.getCpf(), dto.getPhone(), dto.getBirthDate());
    bo.setStatus(dto.getStatus());
    return bo;
  }

  public static ClientBO toBO(PgSqlClientEntity entity) {
    ClientBO bo = new ClientBO(entity.getId(), entity.getName(), entity.getEmail(), entity.getCpf(), entity.getPhone(), entity.getBirthDate());
    bo.setStatus(entity.getStatus());
    return bo;
  }

  public static ClientDTO toDTO(ClientBO bo) {
    ClientDTO dto = new ClientDTO();

    dto.setId(bo.getId());
    dto.setEmail(bo.getEmail());
    dto.setCpf(bo.getCpf());
    dto.setName(bo.getName());
    dto.setStatus(bo.getStatus());
    dto.setPhone(bo.getPhone());

    return dto;
  }

  public static CreateClientResponseDTO toDTO(MySqlClientEntity entity) {
    CreateClientResponseDTO dto = new CreateClientResponseDTO();

    dto.setId(entity.getId());
    dto.setEmail(entity.getEmail());
    dto.setCpf(entity.getCpf());
    dto.setName(entity.getName());
    dto.setStatus(entity.getStatus());
    dto.setPhone(entity.getPhone());

    return dto;
  }

  public static MySqlClientEntity toMySqlEntity(ClientBO bo) {
    MySqlClientEntity entity = new MySqlClientEntity();

    entity.setId(bo.getId());
    entity.setName(bo.getName());
    entity.setEmail(bo.getEmail());
    entity.setCpf(bo.getCpf());
    entity.setPhone(bo.getPhone());
    entity.setStatus(bo.getStatus());

    return entity;
  }

  public static PgSqlClientEntity toPgSqlEntity(ClientBO bo) {
    PgSqlClientEntity entity = new PgSqlClientEntity();

    entity.setId(bo.getId());
    entity.setName(bo.getName());
    entity.setEmail(bo.getEmail());
    entity.setCpf(bo.getCpf());
    entity.setPhone(bo.getPhone());
    entity.setStatus(bo.getStatus());
    entity.setBirthDate(bo.getBirthDate());

    return entity;
  }

}
