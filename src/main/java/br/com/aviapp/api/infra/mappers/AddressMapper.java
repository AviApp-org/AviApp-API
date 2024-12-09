package br.com.aviapp.api.infra.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.domain.entities.AddressBO;
import br.com.aviapp.api.infra.mysql.models.MySqlAddressEntity;

public class AddressMapper {
    public static AddressBO toBO(MySqlAddressEntity entity) {
        AddressBO bo = new AddressBO(entity.getId(), entity.getStreet(), entity.getNumber(), entity.getCep(),
                entity.getNeighborhood(), entity.getCity(), entity.getState());
        return bo;
    }

    public static MySqlAddressEntity toEntity(AddressBO bo) {
        MySqlAddressEntity entity = new MySqlAddressEntity();
        entity.setId(bo.getId());
        entity.setStreet(bo.getStreet());
        entity.setNumber(bo.getNumber());
        entity.setCep(bo.getCep());
        entity.setNeighborhood(bo.getNeighborhood());
        entity.setCity(bo.getCity());
        entity.setState(bo.getState());
        return entity;
    }

    public static List<AddressBO> toBOList(List<MySqlAddressEntity> entities) {
        return entities.stream()
                .map(AddressMapper::toBO)
                .collect(Collectors.toList());
    }

    public static List<MySqlAddressEntity> toEntityList(List<AddressBO> bos) {
        return bos.stream()
                .map(AddressMapper::toEntity)
                .collect(Collectors.toList());
    }

}
