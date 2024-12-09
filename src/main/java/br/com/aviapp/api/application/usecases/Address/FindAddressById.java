package br.com.aviapp.api.application.usecases.Address;

import java.util.Optional;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class FindAddressById {
    private final AddressRepository repository;

    public FindAddressById(AddressRepository repository) {
        this.repository = repository;
    }

    public Optional<AddressBO> procurarEnderecoPorID(Long addressID) {
        return repository.findAddress(addressID);
    }
}
