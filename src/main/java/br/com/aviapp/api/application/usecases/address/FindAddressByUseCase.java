package br.com.aviapp.api.application.usecases.address;

import java.util.Optional;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class FindAddressByUseCase {
    private final AddressRepository repository;

    public FindAddressByUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public Optional<AddressBO> invoke(Long addressID) {
        return repository.findAddress(addressID);
    }
}
