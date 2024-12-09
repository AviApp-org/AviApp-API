package br.com.aviapp.api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.domain.entities.AddressBO;

public class AddressMapper {
    
    public static AddressBO toBO (AddressDTO dto){
        AddressBO bo = new AddressBO(dto.getId(), dto.getStreet(), dto.getNumber(), dto.getCep(), dto.getNeighborhood(), dto.getCity(), dto.getState());
    return bo;
    }

    public static AddressDTO toDTO (AddressBO bo){
        AddressDTO dto = new AddressDTO();
        dto.setId(bo.getId());
        dto.setStreet(bo.getStreet());
        dto.setNumber(bo.getNumber());
        dto.setCep(bo.getCep());
        dto.setNeighborhood(bo.getNeighborhood());
        dto.setCity(bo.getCity());
        dto.setState(bo.getState());
        return dto;
    }

    public static List<AddressBO> toBOList(List<AddressDTO> dtos) {
        return dtos.stream()
                .map(AddressMapper::toBO)
                .collect(Collectors.toList());
    }

    public static List<AddressDTO> toDTOList(List<AddressBO> bos) {
        return bos.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }
}
