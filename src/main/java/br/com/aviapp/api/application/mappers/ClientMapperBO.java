package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.domain.entities.ClientBO;

public class ClientMapperBO {

  public ClientBO toBO(ClientDTO dto) {
    return new ClientBO(
        dto.id(),
        dto.name(),
        dto.email(),
        dto.cnpj(),
        dto.phone(),
        dto.status());
  }

  public ClientDTO toDTO(ClientBO bo) {
    return new ClientDTO(
        bo.getId(),
        bo.getName(),
        bo.getEmail(),
        bo.getCnpj(),
        bo.getPhone(),
        bo.getStatus());
  }

  public List<ClientBO> toBOList(List<ClientDTO> dtos) {
    return dtos.stream()
        .map(this::toBO)
        .collect(Collectors.toList());
  }

  public List<ClientDTO> toDTOList(List<ClientBO> bos) {
    return bos.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }
}
