package br.com.aviapp.api.application.usecases.address;

import br.com.aviapp.api.application.gateways.AddressRepository;

public class DeleteAddressUseCase {

    private final AddressRepository repository;

    public DeleteAddressUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public void invoke (Long addressID){

        repository.deleteAddress(addressID);
    }
}
