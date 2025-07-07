package br.com.aviapp.api.application.usecases.address;

import java.util.Optional;

import br.com.aviapp.api.application.dto.AddressDTO;
import br.com.aviapp.api.application.gateways.IAddress;

public class UpdateAddressUseCase {

    private final IAddress repository;

    public UpdateAddressUseCase(IAddress repository) {
        this.repository = repository;
    }

    public Optional<AddressDTO> invoke(Long addressID, AddressDTO updatedAddressDTO) {
        return repository.updateAddress(addressID, updatedAddressDTO);
    }
}
