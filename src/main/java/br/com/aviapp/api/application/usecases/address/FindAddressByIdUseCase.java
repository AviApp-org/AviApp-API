package br.com.aviapp.api.application.usecases.address;

import java.util.Optional;

import br.com.aviapp.api.application.dto.AddressDTO;
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
        return repository.findAddress(addressID)
                .map(mapper::toBO)  // Convert to domain for any business rules
                .map(mapper::toDTO); // Convert back to DTO for response
    }
    
}
