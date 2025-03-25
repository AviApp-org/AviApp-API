package br.com.aviapp.api.application.usecases.address;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.application.mappers.AddressMapperBO;
import br.com.aviapp.api.domain.entities.AddressBO;

public class CreateAddressUseCase {

    private final AddressRepository repository;
    private final AddressMapperBO mapper;

    public CreateAddressUseCase(AddressRepository repository, AddressMapperBO mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AddressDTO invoke(AddressDTO addressDTO) {

        AddressBO addressBO = mapper.toBO(addressDTO);

        AddressDTO validatedAddress = mapper.toDTO(addressBO);

        return repository.createAddress(validatedAddress);
    }

}
