package br.com.aviapp.api.application.usecases.address;

import java.util.Optional;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.gateways.AddressRepository;

public class UpdateAddressUseCase {
        private final AddressRepository repository;

    public UpdateAddressUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public Optional<AddressDTO> invoke(Long addressID, AddressDTO updatedAddressDTO) {
        return repository.updateAddress(addressID, updatedAddressDTO);
    }
}
