package br.com.aviapp.api.application.usecases.address;

import java.util.Optional;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.exceptions.NotFoundException;
import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.application.mappers.AddressMapperBO;

public class FindAddressByIdUseCase {
    private final AddressRepository repository;
    private final AddressMapperBO mapper;

    public FindAddressByIdUseCase(AddressRepository repository, AddressMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<AddressDTO> invoke(Long addressID) {
        return Optional.ofNullable(repository.findAddress(addressID)
                .map(mapper::toBO)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundException(addressID)));

    }
    
}
