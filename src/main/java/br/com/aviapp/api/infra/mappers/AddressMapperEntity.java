package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;
import br.com.aviapp.api.infra.mysql.models.MySqlFarmEntity;
import br.com.aviapp.api.infra.mysql.repository.FarmRepositoryJPA;

public class AddressMapperEntity {
    private final FarmRepositoryJPA farmRepositoryJPA;

    public AddressMapperEntity(FarmRepositoryJPA farmRepositoryJPA) {
        this.farmRepositoryJPA = farmRepositoryJPA;
    }


    public AddressDTO toDTO(MySqlAddressEntity entity) {
        return new AddressDTO(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getCep(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState(),
                entity.getFarm().getId());
    }

    public MySqlAddressEntity toEntity(AddressDTO dto) {
        MySqlFarmEntity mySqlFarmEntity = farmRepositoryJPA.findById(dto.farmId()).orElseThrow();
        return new MySqlAddressEntity(
                dto.id(),
                dto.street(),
                dto.number(),
                dto.cep(),
                dto.neighborhood(),
                dto.city(),
                dto.state(),
                mySqlFarmEntity
                );
    }

    public List<AddressDTO> toDTOList(List<MySqlAddressEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MySqlAddressEntity> toEntityList(List<AddressDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
