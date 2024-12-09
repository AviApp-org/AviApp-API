package br.com.aviapp.api.application.usecases.Address;

import java.util.Optional;

import br.com.aviapp.api.application.gateways.AddressRepository;
import br.com.aviapp.api.domain.entities.AddressBO;

public class UpdateAddress {
        private final AddressRepository repository;

    public UpdateAddress(AddressRepository repository) {
        this.repository = repository;
    }

    public Optional<AddressBO> atualizarEndereco(Long addressID, AddressBO updatedAddressBO) {
        return repository.findAddress(addressID);
    }
}
