package br.com.aviapp.api.application.usecases.address;

import br.com.aviapp.api.application.gateways.IAddress;

public class DeleteAddressUseCase {

    private final IAddress repository;

    public DeleteAddressUseCase(IAddress repository) {
        this.repository = repository;
    }

    public void invoke (Long addressID){

        repository.deleteAddress(addressID);
    }
}
