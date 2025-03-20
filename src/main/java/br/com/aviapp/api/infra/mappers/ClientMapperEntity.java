package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.ClientDTO;
import br.com.aviapp.api.domain.enums.EnumStatusCliente;
import br.com.aviapp.api.infra.mysql.models.MySqlClientEntity;
import br.com.aviapp.api.infra.mysql.enums.ClientStatusType;

public class ClientMapperEntity {
    
    public ClientDTO toDTO(MySqlClientEntity entity) {
        return new ClientDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCnpj(),
                entity.getPhone(),
                EnumStatusCliente.valueOf(entity.getStatus().name())
                );
    }

    public MySqlClientEntity toEntity(ClientDTO dto) {
        return new MySqlClientEntity(
                dto.id(),
                dto.name(),
                dto.cnpj(),
                dto.email(),
                dto.phone(),
                ClientStatusType.valueOf(dto.status().name()));
    }

    public List<ClientDTO> toDTOList(List<MySqlClientEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlClientEntity> toEntityList(List<ClientDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
