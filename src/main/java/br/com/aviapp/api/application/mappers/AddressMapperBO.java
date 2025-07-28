package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.domain.entities.AddressBO;

public class AddressMapperBO {

    public AddressBO toBO(AddressDTO dto) {
        return new AddressBO(
                dto.id(),
                dto.street(),
                dto.number(),
                dto.cep(),
                dto.neighborhood(),
                dto.city(),
                dto.state()
        );
    }

    public AddressDTO toDTO(AddressBO bo) {
        return new AddressDTO(
                bo.getId(),
                bo.getStreet(),
                bo.getNumber(),
                bo.getCep(),
                bo.getNeighborhood(),
                bo.getCity(),
                bo.getState()
        );
    }

    public List<AddressBO> toBOList(List<AddressDTO> dtos) {
        return dtos.stream()
                .map(this::toBO)
                .collect(Collectors.toList());
    }

    public List<AddressDTO> toDTOList(List<AddressBO> bos) {
        return bos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
