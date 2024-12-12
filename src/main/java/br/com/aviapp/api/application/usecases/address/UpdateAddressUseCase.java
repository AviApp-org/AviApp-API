package br.com.aviapp.api.application.usecases.address;

import java.util.Optional;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class UpdateAddressUseCase {
        private final AddressRepository repository;

    public UpdateAddressUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public Optional<AddressBO> invoke(Long addressID, AddressBO updatedAddressBO) {
        return repository.findAddress(addressID);
    }
}
